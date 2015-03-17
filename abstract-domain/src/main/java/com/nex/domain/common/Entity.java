package com.nex.domain.common;


public interface Entity {

	Object getId();
	Entity merge();
	void persist();
	void flush();
	void remove();
	
}
