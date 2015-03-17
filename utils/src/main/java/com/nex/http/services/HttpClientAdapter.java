package com.nex.http.services;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.nex.http.HttpResponse;
import com.nex.http.HttpRestClient;
import com.nex.http.SSLCertificateDelegate;
import com.nex.http.HttpRestClient.RequestMethod;

public class HttpClientAdapter {
	public static class HttpResult {
		private int code;
		private String response;
		
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
	}
	
	HttpRestClient client = new HttpRestClient(true);
	
	SSLCertificateDelegate delegate;
	
	public SSLCertificateDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(SSLCertificateDelegate delegate) {
		this.delegate = delegate;
		client.setSSLDelegate(delegate);
	}
	
	protected String encodeParam(String s) throws Exception {
		if(StringUtils.isEmpty(s))
			return "";
		return URLEncoder.encode(s, "UTF-8");
	}
	
	public HttpResult Get(String url) throws Exception {
		return Get(url, null, null, null);
	}
	
	public HttpResult Get(String url, Map<String, String> params) throws Exception {
		return Get(url, params, null, null);
	}
	
	public HttpResult Get(String url, Map<String, String> params, String userName, String password) throws Exception {
		return Method(url, params, RequestMethod.GET, userName, password);
	}
	
	public HttpResult Post(String url) throws Exception {
		return Get(url, null, null, null);
	}
	
	public HttpResult Post(String url, Map<String, String> params) throws Exception {
		return Get(url, params, null, null);
	}
	
	public HttpResult Post(String url, Map<String, String> params, String userName, String password) throws Exception {
		return Method(url, params, RequestMethod.POST, userName, password);
	}	
	
	protected HttpResult Method(String url, Map<String, String> params, RequestMethod method, String userName, String password) throws Exception {
		Map<String, String> headers = new HashMap<>();
		String pstr = null;
		
		if(params!=null && params.size()>0) {
			StringBuilder sb = new StringBuilder();
			
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if(sb.length()>0)
					sb.append("&");
				sb.append(encodeParam(entry.getKey()) + "=" + encodeParam(entry.getValue()));
			}		
			
			pstr=sb.toString();
		}
		
		if(userName!=null && password!=null) {
			String encoded = Base64.getEncoder().encodeToString((userName+":"+password).getBytes());
			headers.put("Authorization", "Basic " + encoded);
		}
		
		HttpResponse r = client.send(url, method, pstr, headers);
		
		HttpResult rv = new HttpResult();
		rv.setCode(r.getResponseCode());
		rv.setResponse(r.getResult(true).toString());
		
		return rv;
	}
}
