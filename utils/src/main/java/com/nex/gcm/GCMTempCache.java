package com.nex.gcm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GCMTempCache {

	private Map<Long, Set<String>> drivers = new HashMap<>();
	
	
	public void put(Long driverId, String registrationId) {
		Set<String> list = this.drivers.get(driverId);
		if(list == null) {
			list = new HashSet<>();
			this.drivers.put(driverId, list);
		}
		list.add(registrationId);
	}
	
	public Set<String> getRegistrationIds(Long driverId) {
		Set<String> list = this.drivers.get(driverId);
		if(list == null) {
			list = new HashSet<>();
		}
		return list;
	}
	
}
