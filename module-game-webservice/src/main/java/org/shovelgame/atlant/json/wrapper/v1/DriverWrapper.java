package org.shovelgame.atlant.json.wrapper.v1;

import org.shovelgame.domain.Address;

public class DriverWrapper {

	private Long id;
	private Address address;
	private Long homePhone;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(Long homePhone) {
		this.homePhone = homePhone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
