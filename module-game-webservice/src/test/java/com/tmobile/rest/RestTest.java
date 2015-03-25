package com.tmobile.rest;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.shovelgame.atlant.json.wrapper.v1.CustomerWrapper;
import org.shovelgame.atlant.json.wrapper.v1.DriverWrapper;
import org.shovelgame.atlant.json.wrapper.v1.OrderWrapper;
import org.shovelgame.domain.CapacityType;
import org.shovelgame.domain.Customer;
import org.shovelgame.http.HttpResponse;
import org.shovelgame.http.HttpRestClient;
import org.shovelgame.http.HttpRestClient.RequestMethod;
import org.shovelgame.http.oauth.OAuthClient;
import org.shovelgame.http.oauth.OAuthClient.Token;
import org.shovelgame.utils.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTest {
	@Test
	public void testReflection() throws Exception {
		Customer c = new Customer();
		ReflectionUtils.setValue("cellPhone", c, 123L);
	}

	@Test
	public void testRegistrationCustomerWrapperControllerV1() throws Exception {
		String url = "http://localhost:8080/atlant-taxi/ws/v1/registration/";
		HttpRestClient client = new HttpRestClient(getAnonymouseToken());

		CustomerWrapper w = new CustomerWrapper();
		w.setFirstName("Test");
		w.setLastName("Test");
		w.setPassword("passeword");
		w.setPhoneNumber(2123156789L);

		String json = new ObjectMapper().writeValueAsString(w);

		// DO LIST
		HttpResponse response = client.send(url, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());

		// DO CREATE
		response = client.send(url, RequestMethod.POST, json, headers());
		System.out.println(response.getResult());
		Assert.assertEquals(200, response.getResponseCode());
		

		// GET ID
		Object id = (Object) response.getResultAsJsonMap().get("id");
		Assert.assertNotNull(id);

		// DO DETAIL
		response = client.send(url + "/" + id, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
		w = response.getResultAsJson(CustomerWrapper.class);
		w.setPhoneNumber(null);

		// do error
		json = new ObjectMapper().writeValueAsString(w);
		// DO UPDATE
		response = client.send(url + "/" + id, RequestMethod.PUT, json, headers());
		Assert.assertEquals(200, response.getResponseCode());

		// DO DELETE
		response = client.send(url + "/" + id, RequestMethod.DELETE);
		Assert.assertEquals(200, response.getResponseCode());
	}

	@Test
	public void testOrderControllerWrapperV1() throws Exception {
		String url = "http://localhost:8080/atlant-taxi/ws/v1/order/";
		HttpRestClient client = new HttpRestClient(getToken());

		OrderWrapper w = new OrderWrapper();
		w.setLatitudeEnd(50.2);
		w.setLatitudeStart(69.6);
		w.setLongitudeEnd(6.9);
		w.setLongitudeStart(7.);
		w.setPickupTime(Calendar.getInstance());
		w.setCapacityType(CapacityType.C7TO8);

		String json = new ObjectMapper().writeValueAsString(w);

		// DO LIST
		HttpResponse response = client.send(url, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());

		// DO CREATE
		response = client.send(url, RequestMethod.POST, json, headers());
		Assert.assertEquals(200, response.getResponseCode());
		System.out.println(response.getResult());

		// GET ID
		Object id = (Object) response.getResultAsJsonMap().get("id");
		Assert.assertNotNull(id);

		// DO DETAIL
		response = client.send(url + "/" + id, RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
		w = response.getResultAsJson(OrderWrapper.class);

		// add driver to order
		DriverWrapper driver = new DriverWrapper();
		driver.setId(14L);
		w.setDriver(driver);
		json = new ObjectMapper().writeValueAsString(w);

		// DO UPDATE
		response = client.send(url + "/" + id, RequestMethod.PUT, json, headers());
		Assert.assertEquals(200, response.getResponseCode());

		// DO DELETE
		response = client.send(url + "/" + id, RequestMethod.DELETE);
		Assert.assertEquals(200, response.getResponseCode());
	}

	@Test
	public void test() throws Exception {
		HttpRestClient client = new HttpRestClient(getToken());
		HttpResponse response = client.send("http://localhost:8080/atlant-taxi/ws/order/", RequestMethod.GET);
		Assert.assertEquals(200, response.getResponseCode());
	}

	private Token getAnonymouseToken() throws Exception {
		OAuthClient cl = new OAuthClient("http://srvlbx038.tsczsi.local/oauth-server/oauth/token?grant_type=client_credentials");
		cl.setEncodedClientAuthorization(Base64.getEncoder().encodeToString("abcd:dcba".getBytes()));
		return cl.getToken();
	}

	private Token getToken() throws Exception {
		OAuthClient cl = new OAuthClient("http://srvlbx038.tsczsi.local/oauth-server/oauth/token?grant_type=password");
		cl.setEncodedClientAuthorization(Base64.getEncoder().encodeToString("abcd:dcba".getBytes())).setUsername("603422142").setPassword("test");
		return cl.getToken();
	}

	private Map<String, String> headers() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		return headers;
	}
}
