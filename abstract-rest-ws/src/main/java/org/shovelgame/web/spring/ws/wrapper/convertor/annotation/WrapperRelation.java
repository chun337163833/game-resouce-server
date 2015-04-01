package org.shovelgame.web.spring.ws.wrapper.convertor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.shovelgame.web.spring.ws.wrapper.BasicRooBasedEntityFinderService;
import org.shovelgame.web.spring.ws.wrapper.FinderService;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface WrapperRelation {
	
	/**
	 * Value of field on target object;
	 * @return
	 */
	String value() default "";
	
	/**
	 * Name of id property in target class for use in FinderService
	 * @return
	 */
	String idName() default "id";
	
	/**
	 * Finder service is used for finding persistence object when converting wrapper to target object
	 * @return
	 */
	Class<? extends FinderService<? extends Object>> service() default BasicRooBasedEntityFinderService.class;
	
	/**
	 * When true allow injecting nested properties of target nested object, 
	 * Usable only without MappedProperty
	 * Use @see {@link WrapperIgnoreReverse} for ignore injection of selected properties.
	 * @return
	 */
	boolean allowNestedInjection() default false;
}
