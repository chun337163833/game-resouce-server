package com.nex.domain.common;


public interface Entity extends JsonObject {

	Object getId();
	Entity merge();
	void persist();
	void flush();
	void remove();
	
}
