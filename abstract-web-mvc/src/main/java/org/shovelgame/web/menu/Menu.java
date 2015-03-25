package org.shovelgame.web.menu;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Menu {
	/**
	 * LocalizedCode
	 * @return
	 */
	String name();

	
	/**
	 * LocalizedCode
	 * @return
	 */
	String group() default MenuFactory.ROOT_MENU;
	String groupOf() default MenuFactory.ROOT_MENU;
	boolean isGroup() default false;
	int order() default 0;
}
