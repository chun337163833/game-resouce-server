package com.nex.rest;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import com.nex.http.HttpResponse;
import com.nex.http.HttpRestClient;
import com.nex.http.HttpRestClient.RequestMethod;
import com.nex.http.oauth.OAuthClient;
import com.nex.http.oauth.OAuthClient.Token;

public class RestTest {

	
	@Test
	public void test() throws Exception {
		HttpRestClient client = new HttpRestClient(getToken());
		HttpResponse response = client.send("http://localhost:8080/atlant-taxi/ws/order/", RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
	}
	
	
	private Token getToken() throws Exception {
		OAuthClient cl = new OAuthClient("http://srvlbx038.tsczsi.local/oauth-server/oauth/token?grant_type=password");
		cl.setEncodedClientAuthorization(Base64.getEncoder().encodeToString("abcd:dcba".getBytes()))
		.setUsername("603422142")
		.setPassword("test");
		return cl.getToken();
	}
	
}
