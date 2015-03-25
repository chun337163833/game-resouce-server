package org.shovelgame.http;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.shovelgame.http.oauth.OAuthClient.Token;

public class HttpRestClient {
	public static final int CONN_TIMEOUT = 30000;
	public static final int READ_TIMEOUT = 20000;
	
	public enum RequestMethod {
		POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE");
		private String method;

		private RequestMethod(String method) {
			this.method = method;
		}

		public String getMethod() {
			return method;
		}
	}

	private Token token;
	private SSLCertificateDelegate sslDelegate;
	private Object sslDelegateLock = new Object();
	private int connectionTimeout = CONN_TIMEOUT;
	private int readTimeout = READ_TIMEOUT;
	private SSLContext cachedContext;
	private volatile boolean cacheContext;

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public HttpRestClient(Token token) {
		super();
		this.token = token;
	}

	public HttpRestClient(SSLCertificateDelegate certificate) {
		super();
	}

	public HttpRestClient() {
		super();
	}
	
	public HttpRestClient(boolean cacheContext) {
		super();
		this.cacheContext = cacheContext;
	}	

	public void setSSLDelegate(SSLCertificateDelegate sslDelegate) {
		synchronized (sslDelegateLock) {
			this.sslDelegate = sslDelegate;	
		}
	}
	
	public boolean isCacheContext() {
		return cacheContext;
	}

	public void setCacheContext(boolean cacheContext) {
		this.cacheContext = cacheContext;
	}

	public SSLContext trustHosts() throws Exception {
		if(isCacheContext() && cachedContext!=null)
			return cachedContext;
		
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		KeyStore ks = sslDelegate.keystoreType();
		InputStream in = sslDelegate.keystoreStream();
		String keyPassword = sslDelegate.password();
		if(keyPassword!=null)
			ks.load(in, keyPassword.toCharArray());
		in.close();
		tmf.init(ks);
		TrustManager[] tms = tmf.getTrustManagers();
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, tms, new java.security.SecureRandom());
		
		if(isCacheContext())
			cachedContext = sc;
		
		return sc;
	}

	public HttpResponse send(String url, RequestMethod method, Map<String, String> headers) throws Exception {
		return this.send(url, method, null, headers);
	}

	public HttpResponse send(String url, RequestMethod method, String params) throws Exception {
		return this.send(url, method, params, null);
	}

	public HttpResponse send(String url, RequestMethod method) throws Exception {
		return this.send(url, method, null, null);
	}

	public HttpResponse send(String url, RequestMethod method, String params, Map<String, String> headers) throws Exception {
		String requestParameters = null;
		if(params != null && RequestMethod.GET.equals(method)) {
			if(params.startsWith("?"))
				url += params;
			else 
				url += "?" + params;
		} else {
			requestParameters = params;
		}
		URL obj = new URL(url);
		URLConnection connection = obj.openConnection();
		HttpURLConnection con = (HttpURLConnection) connection;
		
		con.setConnectTimeout(connectionTimeout);
		con.setReadTimeout(readTimeout);		
		
		if (con instanceof HttpsURLConnection) {
			HttpsURLConnection https = (HttpsURLConnection) con;
			synchronized (sslDelegateLock) {
				if(this.sslDelegate != null) {
					https.setSSLSocketFactory(trustHosts().getSocketFactory());
				}
			}
		}
		con.setDoInput(true);

		con.setRequestMethod(method.getMethod());
		if (headers == null) {
			headers = new HashMap<>();
		}
		if (this.token != null) {
			headers.put("Authorization", "Bearer " + this.token.getAccessToken());
		}
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			con.setRequestProperty(entry.getKey(), entry.getValue());
		}
		if (requestParameters != null) {
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(requestParameters);
			wr.flush();
			wr.close();
		}
		return new HttpResponse(con);
	}

}
