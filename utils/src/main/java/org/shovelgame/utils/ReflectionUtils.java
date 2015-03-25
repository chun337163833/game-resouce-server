package org.shovelgame.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	private static Logger log = LoggerFactory.getLogger(ReflectionUtils.class);
	
	/**
	 * Return value from getter by simple fieldName
	 * @param data
	 * @param key
	 * @return
	 */
	public static Object reflectValueSimple(Object data, String key) {
		return reflectValueSimple(data, key, false);
	}
	public static Object reflectValueSimple(Object data, String key, boolean makeAccessible) {
		if(data == null) return null;
		ArrayMetadata amtd = getArrayData(key);
		Method method = org.springframework.util.ReflectionUtils.findMethod(data.getClass(), getMethodName(amtd.fieldName));
		if(method == null) {
			log.warn("getter not found for " + key  + " in class " + data.getClass());
			return null;
		}
		boolean isAccesible = method.isAccessible();
		if(makeAccessible)
			method.setAccessible(true);
		try {
			Object _result = org.springframework.util.ReflectionUtils.invokeMethod(method, data);
			if(amtd.isArray) {
				_result = getObjectFromArray(amtd, _result);
			}
			return _result;
		} finally {
			method.setAccessible(isAccesible);
		}
	}

	public static void setNestedValue(String path, Object instance, Object value) throws Exception {
		String[] keys = path.split("\\.");
		if(keys.length == 1) {
			setValue(path, instance, value);
			return;
		}
		Object source = instance;
		for(int i = 0; i < keys.length; i++) {
			String k = keys[i];
			Class<?> iclass = source.getClass();
			Field f = iclass.getDeclaredField(k);
			Object ivalue = getFieldValue(f, source);
			if(i == keys.length - 1) {
				setValue(k, source, value);
			} else if(ivalue == null) {
				ivalue = f.getType().newInstance();
				setNestedValue(k, instance, ivalue);
			}
			
			source = ivalue;
		}
	}
	
	public static Field getDeclaredFieldFromAll(String name, Class<?> clz) throws NoSuchFieldException {
		if(clz==null) {
			throw new NoSuchFieldException(name);
		}
		try {
			return clz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			return getDeclaredFieldFromAll(name, clz.getSuperclass());
		} catch (SecurityException e) {
			throw e;
		}
	}
	
	
	public static TypeDescriptor getNestedFieldTypeDescriptor(String path, Object instance) {
		String[] keys = path.split("\\.");
		
		try {		
			if(keys.length == 1) {
				Class<?> iclass = instance.getClass();
				Field f = getDeclaredFieldFromAll(keys[0], iclass);
				f.setAccessible(true);
				return new TypeDescriptor(f);
			}
		} catch(Exception e) {
			log.error("could not get declared field", e);
			return null;
		}
		
		try {
			Object source = instance;
			for(int i = 0; i < keys.length; i++) {
				String k = keys[i];
				Class<?> iclass = source.getClass();
				Field f = getDeclaredFieldFromAll(k, iclass);
				Object ivalue = getFieldValue(f, source);
				if(i == keys.length - 1) {
					f.setAccessible(true);
					return new TypeDescriptor(f);
				}
				source = ivalue;
			}
		} catch (Exception e) {
			log.error("could not get declared field", e);
			return null;
		}
		
		return null;
	}	
	
	public static void setValue(String path, Object instance, Object value) throws Exception {
		Field f = findDeclaredField(instance.getClass(), path);
		if(f == null) {
			log.warn(String.format("No field $1%s found on class $2%s", path, instance.getClass().getSimpleName()));
			return;
		}
		boolean accesible = f.isAccessible();
		try {
			f.setAccessible(true);
			f.set(instance, value);
		} finally {
			f.setAccessible(accesible);
		}
	}
	
	public static Field findDeclaredField(Class<?> instance, String fieldName) throws NoSuchFieldException, SecurityException {
		try {
			return instance.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class<?> superClass = instance.getSuperclass();
			if(!superClass.equals(Object.class))
			return findDeclaredField(superClass, fieldName);
		}
		return null;
	}
	
	public static ArrayMetadata getArrayData(String key) {
		ArrayMetadata metadata = new ArrayMetadata();
		String[] spl = key.split("\\[|\\]");
		if(spl.length > 1) {
			metadata.isArray = Boolean.TRUE;
			metadata.key = spl[1];
		}
		metadata.fieldName = spl[0];
		return metadata;
	}
	
	private static Object getObjectFromArray(ArrayMetadata ai, Object array) {
		if (array instanceof List) {
			List<?> list = (List<?>) array;
			if(list.size() > ai.getArrayIndex())
				return list.get(ai.getArrayIndex());
			else
				return null;
		} else if (array != null && array.getClass().isArray()) {
			Object[] objArray = (Object[]) array;
			if(objArray.length > ai.getArrayIndex())
				return objArray[ai.getArrayIndex()];
			else
				return null;
		} else if (array instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) array;
			return map.get(ai.key);
		} else if(array == null) {
			return null;
		}
		throw new RuntimeException("Field " + ai.fieldName
				+ " is not type of Array, List or Map.");
	}
	
	public static Object getFieldValue(Field field, Object target) {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			return getField(field, target);
		} finally {
			field.setAccessible(accessible);
		}
		
	}
	
	public static Object reflectStaticValue(Class<?> cls, String constantName) {
		try {
			Field field = cls.getDeclaredField(constantName);
			return field.get(null);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}
	
	/**
	 * key can be defined by dot notation. such as entity.employee.department.longName
	 * @param data
	 * @param key
	 * @return
	 */
	public static Object reflectValue(Object data, String key) {
		String[] keys = key.split("\\.");
		if(keys.length == 1) {
			return reflectValueSimple(data, key);
		}
		Object value = null;
		for(String k: keys) {
			value = reflectValue(data, k);	
			data = value;
		}
		return value;
	}
	
	public static String getMethodName(String key){
		String firstChar = key.substring(0,1);
		return "get" + key.replaceFirst(firstChar, firstChar.toUpperCase());
	}
	
	public static <T> boolean hasInterface(Class<?> dataClass, Class<T> requiredInterface) {
		Class<?>[] interfaces = dataClass.getInterfaces();
		for(Class<?> intrf: interfaces) {
			if(intrf.equals(requiredInterface)) {
				return true;
			}
		}
		return false;
	}
	public static <T> boolean isChildOf(Class<?> dataClass, Class<T> requiredClass) {
		return isChildOf(dataClass, requiredClass, true);
	}
	public static <T> boolean isChildOf(Class<?> dataClass, Class<T> requiredClass, boolean isInterface) {
		boolean result = dataClass.equals(requiredClass);
		/* 
		 * Object.class je posledni mozna dedicnost a tu uz kontrolovat nepotrebujeme. 
		 * Nevim jestli se to takhle dela spravne, ale nic jineho me nenapadlo.
		 */
		if(!result && isInterface) {
			result = hasInterface(dataClass, requiredClass);
		}
		if(!result && !dataClass.equals(Object.class)) {
			result = isChildOf(dataClass.getSuperclass(), requiredClass);
		}
		return result;
	}

	public static List<Class<?>> findClasses(String packageName, Class<? extends Annotation> annotation) {
		Reflections reflections = new Reflections(packageName);
		if(annotation == null) {
			return new ArrayList<>(reflections.getSubTypesOf(Object.class));
		} else {
			return new ArrayList<>(reflections.getTypesAnnotatedWith(annotation));
		}
	    
	}
	
	public static List<Class<?>> findClasses(String packageName) {
		return findClasses(packageName, null);
	}

	public static class ArrayMetadata {
		private String fieldName;
		private String key;
		public Integer getArrayIndex() {
			return Integer.valueOf(key);
		}
		private Boolean isArray = Boolean.FALSE;
		
		public String getKey() {
			return key;
		}
		public String getFieldName() {
			return fieldName;
		}
		public Boolean getIsArray() {
			return isArray;
		}
	}
}
