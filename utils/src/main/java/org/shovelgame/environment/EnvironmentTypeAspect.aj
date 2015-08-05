package org.shovelgame.environment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public aspect EnvironmentTypeAspect {

	before(Environment type): @annotation(type) {
		EnvironmentType env = EnvironmentAccessor.getEnvironment();
		for(EnvironmentType e: type.value()) {
			if(e.equals(env)) {
				return;
			}
		}
		this.throwException(type, env);
	}
	
	private void throwException(Environment type, EnvironmentType env) {
		Class<? extends EnvironmentException> cls = type.exception();
		try {
			Constructor<?> ctor = cls.getConstructor(EnvironmentType.class);
			throw (EnvironmentException) ctor.newInstance(env);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Class must have constructor with one argument of type " + EnvironmentType.class, e);
		} catch (SecurityException e) {
			throw new RuntimeException("Constructor must be public.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Cannot instantiate " + cls, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot access to constructor.", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
}
