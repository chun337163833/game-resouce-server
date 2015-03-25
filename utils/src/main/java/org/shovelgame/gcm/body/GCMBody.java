package org.shovelgame.gcm.body;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GCMBody {

	@JsonProperty(value = "registration_ids")
	private String[] registrationIds = new String[0];

	private GCMData data;

	public String[] getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(String[] registrationIds) {
		this.registrationIds = registrationIds;
	}

	public GCMData getData() {
		return data;
	}

	public void setData(GCMData data) {
		this.data = data;
	}

}
