package org.shovelgame.web.spring.ws.wrapper;

import java.lang.reflect.Method;

import org.shovelgame.utils.ReflectionUtils;

public class RooBasedEntityFinderService<E extends Object> implements FinderService<E> {
	Class<E> entityClass = null;

	public RooBasedEntityFinderService(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findById(Object id) {
		String findByIdMethodName = "find" + entityClass.getSimpleName();
		Method findByIdMethod = ReflectionUtils.findMethod(entityClass, findByIdMethodName, Long.class);
		E entity = (E) ReflectionUtils.invokeMethod(findByIdMethod, null, Long.valueOf(id.toString()));
		if (entity == null) {
			throw new RuntimeException("Entity " + entityClass.getSimpleName() + " with id=" + id + " not found.");
		}
		return entity;
	}
}
