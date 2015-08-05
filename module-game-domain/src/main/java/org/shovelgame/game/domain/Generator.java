package org.shovelgame.game.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javassist.Modifier;

import javax.persistence.Table;

import org.shovelgame.utils.ReflectionUtils;

public class Generator {

	
	public static void main(String[] args) {
		List<Class<?>> clss = ReflectionUtils.findClasses(Table.class, "org.shovelgame.game.domain");
		Collections.sort(clss, new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> o1, Class<?> o2) {
				return o1.getCanonicalName().compareTo(o2.getCanonicalName());
			}
		});
		for(Class<?> cls: clss) {
//			if(!Modifier.isAbstract(cls.getModifiers()))
			System.out.println("<class>"+cls.getCanonicalName()+"</class>");
		}
	}
	
}
