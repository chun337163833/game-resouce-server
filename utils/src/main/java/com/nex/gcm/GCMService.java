package com.nex.gcm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nex.gcm.body.GCMBody;
import com.nex.http.HttpResponse;
import com.nex.http.HttpRestClient;
import com.nex.http.HttpRestClient.RequestMethod;

public class GCMService {
	
	@Autowired
	private GCMTempCache cache;
	
	private String apiUrl;
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	private String apiKey;
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void pair(Long driverId, String registrationId) {
		this.cache.put(driverId, registrationId);
	}
	
	public String[] getRegistrationIds(Long driverId) {
		Set<String> ids = this.cache.getRegistrationIds(driverId);
		return ids.toArray(new String[ids.size()]);
	}
	
	public void send(GCMBody body) throws GCMServiceException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(body);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Authorization", "key=" + apiKey);
			headers.put("Content-Type", "application/json");
			HttpRestClient client = new HttpRestClient();
			HttpResponse response = client.send(apiUrl, RequestMethod.POST, str, headers);
			int code = response.getResponseCode();
			if(code != 200) {
				throw new GCMServiceException("Status code: " + code);
			} else {
				StringBuffer result = response.getResult();
				System.out.println(result);
			}
		} catch (Exception e) {
			throw new GCMServiceException(e);
		}
	}
	
}
