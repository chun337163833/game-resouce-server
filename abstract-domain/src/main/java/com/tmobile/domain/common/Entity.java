package com.tmobile.domain.common;


public interface Entity extends JsonResponse {

	Object getId();
	Entity merge();
	void persist();
	void flush();
	void remove();
	
}
