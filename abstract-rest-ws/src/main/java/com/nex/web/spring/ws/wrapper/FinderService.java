package com.nex.web.spring.ws.wrapper;

public interface FinderService<E extends Object> {

	E findById(Object id);
	void setTargetClass(Class<E> cls);
}
