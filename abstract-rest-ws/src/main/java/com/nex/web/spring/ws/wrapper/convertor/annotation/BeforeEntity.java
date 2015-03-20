package com.nex.web.spring.ws.wrapper.convertor.annotation;

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
/**
 * Method annotated by this annotation is called before entity will be updated.
 * @author TremlL
 *
 */
public @interface BeforeEntity {

}
