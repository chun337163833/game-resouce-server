package org.shovelgame.web.spring.ws.wrapper.convertor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented

/**
 * When value of allowNestedInjection in @see {@link WrapperRelation}
 * is true then field annotated by this annotation will be ignored.
 * @author TremlL
 *
 */
public @interface WrapperIgnoreReverse {

}
