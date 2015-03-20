package com.nex.wrapper.conversion.wrapper;

import com.nex.web.spring.ws.wrapper.convertor.annotation.WrapperIgnoreReverse;



public class NestedWrapper {
	
	@WrapperIgnoreReverse
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
