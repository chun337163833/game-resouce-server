package com.nex.domain.common;


public interface Entity extends JsonResponse {

	Object getId();
	Entity merge();
	void persist();
	void flush();
	void remove();
	
}
