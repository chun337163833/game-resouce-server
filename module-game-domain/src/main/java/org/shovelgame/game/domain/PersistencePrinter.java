package org.shovelgame.game.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Table;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.shovelgame.utils.ReflectionUtils;

public class PersistencePrinter {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		List<Class<?>> classes = ReflectionUtils.findClasses(Table.class, "org.shovelgame.game.domain.data","org.shovelgame.game.domain.model","org.shovelgame.game.domain.i18n");
		Collections.sort(classes, new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> o1, Class<?> o2) {
				return o1.getCanonicalName().compareTo(o2.getCanonicalName());
			}
		});
		for(Class<?> cls: classes) {
			System.out.println("<class>"+cls.getCanonicalName() + "</class>");
		}
		System.out.println();
	}

}
