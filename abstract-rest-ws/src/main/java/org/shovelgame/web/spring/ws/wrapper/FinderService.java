package org.shovelgame.web.spring.ws.wrapper;

public interface FinderService<E extends Object> {

	E findById(Object id);
}
