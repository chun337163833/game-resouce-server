package org.shovelgame.environment;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EnvironmentType {

	/**
	 * Set environments which can use this methods.
	 * @return
	 */
	
	Environment[] value();
	
	/**
	 * Defines exception will be thrown when current environment not match with value
	 * @return
	 */
	Class<? extends EnvironmentException> exception() default EnvironmentException.class;
}
