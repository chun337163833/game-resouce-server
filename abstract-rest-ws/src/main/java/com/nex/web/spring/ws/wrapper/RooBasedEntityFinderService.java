package com.nex.web.spring.ws.wrapper;

import java.lang.reflect.Method;

import com.nex.utils.ReflectionUtils;

public class RooBasedEntityFinderService implements FinderService<Object> {
	Class<Object> entityClass = null;

	public RooBasedEntityFinderService(Class<Object> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public Object findById(Object id) {
		String findByIdMethodName = "find" + entityClass.getSimpleName();
		Method findByIdMethod = ReflectionUtils.findMethod(entityClass,
				findByIdMethodName, Long.class);
		Object entity = ReflectionUtils.invokeMethod(findByIdMethod, null, id);
		if (entity == null) {
			throw new RuntimeException("Entity " + entityClass.getSimpleName()
					+ " with id=" + id + " not found.");
		}
		return entity;
	}

}
