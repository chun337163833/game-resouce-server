package com.nex.rest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.nex.http.HttpResponse;
import com.nex.http.HttpRestClient;
import com.nex.http.HttpRestClient.RequestMethod;
import com.nex.http.oauth.OAuthClient;
import com.nex.http.oauth.OAuthClient.Token;

public class RestTest {

	
	@Test
	public void testCRUD() throws Exception {
		String url = "http://localhost:8080/game-server/ws/item";
		HttpRestClient client = new HttpRestClient();
		
		//DO LIST
		HttpResponse response = client.send(url, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
		
		//DO CREATE
		response = client.send(url, RequestMethod.POST, "{\"name\":\"Test Item\"}", headers());
		Assert.assertEquals(200, response.getResponseCode());
		
		//GET ID
		Object id = (Object) response.getResultAsJsonMap().get("id");
		Assert.assertNotNull(id);
		
		//DO DETAIL
		response = client.send(url + "/" + id, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
		
		//DO UPDATE
		response = client.send(url + "/" + id, RequestMethod.PUT, "{\"name\":\"Test Item Changed\"}", headers());
		Assert.assertEquals(200, response.getResponseCode());
		
		//DO DELETE
		response = client.send(url + "/" + id, RequestMethod.DELETE);
		Assert.assertEquals(200, response.getResponseCode());
	}
	
	
	private Token getToken() throws Exception {
		OAuthClient cl = new OAuthClient("http://srvlbx038.tsczsi.local/oauth-server/oauth/token?grant_type=password");
		cl.setEncodedClientAuthorization(Base64.getEncoder().encodeToString("abcd:dcba".getBytes()))
		.setUsername("603422142")
		.setPassword("test");
		return cl.getToken();
	}
	
	private Map<String, String> headers() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return headers;
	}
	
}
