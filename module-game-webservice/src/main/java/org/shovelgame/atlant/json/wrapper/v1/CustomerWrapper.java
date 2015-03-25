package org.shovelgame.atlant.json.wrapper.v1;

import org.shovelgame.domain.Customer;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.AfterEntity;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerWrapper implements JsonObject {

	private Long id;
	
	@WrapperProperty("cellPhone")
	private Long phoneNumber;
	
	@JsonIgnore
	private String password;
	
	private String firstName;
	
	private String lastName;

	@AfterEntity
	public void afterEntityUpdate(Customer customer) {
		if (this.password != null)
			customer.setMatchPassword(this.password);
		if (this.phoneNumber != null)
			customer.setUserName(this.phoneNumber.toString());
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
