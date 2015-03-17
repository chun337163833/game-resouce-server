package com.nex.environment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public aspect EnvironmentTypeAspect {

	before(EnvironmentType type): @annotation(type) {
		Environment env = EnvironmentAccessor.getEnvironment();
		for(Environment e: type.value()) {
			if(e.equals(env)) {
				return;
			}
		}
		this.throwException(type, env);
	}
	
	private void throwException(EnvironmentType type, Environment env) {
		Class<? extends EnvironmentException> cls = type.exception();
		try {
			Constructor<?> ctor = cls.getConstructor(Environment.class);
			throw (EnvironmentException) ctor.newInstance(env);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Class must have constructor with one argument of type " + Environment.class, e);
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
