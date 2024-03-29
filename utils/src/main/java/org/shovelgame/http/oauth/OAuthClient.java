package org.shovelgame.http.oauth;

import java.util.HashMap;
import java.util.Map;

import org.shovelgame.http.HttpResponse;
import org.shovelgame.http.HttpRestClient;
import org.shovelgame.http.HttpRestClient.RequestMethod;
import org.shovelgame.http.SSLCertificateDelegate;

public class OAuthClient {

	private String username;
	private String password;
	private String encodedClientAuthorization;
	private String tokenUrl;

	private Token token;
	private SSLCertificateDelegate sslDelegate;

	public OAuthClient(String tokenUrl) {
		super();
		this.tokenUrl = tokenUrl;
	}

	public OAuthClient setSSLDelegate(SSLCertificateDelegate sslDelegate) {
		this.sslDelegate = sslDelegate;
		return this;
	}

	public OAuthClient setUsername(String username) {
		this.username = username;
		return this;
	}

	public OAuthClient setPassword(String password) {
		this.password = password;
		return this;
	}

	public OAuthClient refreshToken() {
		return this;
	}

	public OAuthClient setEncodedClientAuthorization(String encodedClientAuthorization) {
		this.encodedClientAuthorization = encodedClientAuthorization;
		return this;
	}
	public HttpResponse requestToken() throws Exception {
		return this.requestToken(new HashMap<>());
	}
	public HttpResponse requestToken(Map<String, String> headers) throws Exception {
		HttpRestClient client = new HttpRestClient();
		client.setSSLDelegate(this.sslDelegate);
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put("Authorization", "Basic " + encodedClientAuthorization);
		String params = "grant_type=password&username=" + this.username + "&password=" + this.password;
		if(this.username == null && this.password == null) {
			params = "grant_type=client_credentials";
		}
		HttpResponse response = client.send(tokenUrl, RequestMethod.POST, params, headers);
		return response;
	}

	public Token getToken(HttpResponse response) throws Exception {
		if (this.token != null) {
			return this.token;
		}
		if (response.getResponseCode() == 200) {
			Map<String, Object> values = response.getResultAsJsonMap();
			Object rt = values.get("refresh_token");
			String refreshToken = null;
			if(rt != null) {
				refreshToken = rt.toString();
			}
			this.token = new Token(values.get("access_token").toString(), refreshToken, Long.valueOf(values.get("expires_in")
					.toString()));
		}
		return this.token;
	}

	public Token getToken() throws Exception {
		HttpResponse response = requestToken();
		return this.getToken(response);
	}

	public class Token {

		private Token(String accessToken, String rereshToken, Long expire) {
			super();
			this.accessToken = accessToken;
			this.rereshToken = rereshToken;
			this.expire = expire;
		}

		private String accessToken;
		private String rereshToken;
		private Long expire;

		public String getAccessToken() {
			return accessToken;
		}

		public String getRereshToken() {
			return rereshToken;
		}

		public Long getExpire() {
			return expire;
		}

	}
}
