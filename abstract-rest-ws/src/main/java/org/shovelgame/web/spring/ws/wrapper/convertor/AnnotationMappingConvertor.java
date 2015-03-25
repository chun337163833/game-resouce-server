package org.shovelgame.web.spring.ws.wrapper.convertor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.shovelgame.annotation.Logger;
import org.shovelgame.utils.ReflectionUtils;
import org.shovelgame.utils.StringUtils;
import org.shovelgame.web.spring.ws.wrapper.FinderService;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.AfterEntity;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.AfterWrapper;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.BeforeEntity;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.BeforeWrapper;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperIgnore;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperIgnoreReverse;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperProperty;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperRelation;

@Logger
public class AnnotationMappingConvertor<E extends Object, W extends Object> extends AbstractEntityConvertor<E, W> {

	private Class<E> entityClass;
	private Class<W> wrapperClass;

	public AnnotationMappingConvertor(Class<E> entityClass, Class<W> wrapperClass) {
		super();
		this.entityClass = entityClass;
		this.wrapperClass = wrapperClass;
	}

	@Override
	public void updateWrapper(E entity, W wrapper) {
		Class<W> wclass = wrapperClass;
		Method beforeMethod = this.findAnnotatedMethod(wclass, BeforeWrapper.class);
		if(beforeMethod != null) {
			ReflectionUtils.invokeMethod(beforeMethod, wrapper);
		}
		for (Field f : wclass.getDeclaredFields()) {
			WrapperIgnore ignore = f.getDeclaredAnnotation(WrapperIgnore.class);
			if (ignore != null)
				continue;
			WrapperProperty property = f.getDeclaredAnnotation(WrapperProperty.class);
			WrapperRelation relation = f.getDeclaredAnnotation(WrapperRelation.class);
			
			try {
				if (relation != null) {
					String relationValue = relation.value();
					if(StringUtils.isEmpty(relationValue)) {
						relationValue = f.getName();
					}
					Field tfield = ReflectionUtils.findField(entity.getClass(), relationValue);
					if (tfield == null) {
						throw new NoSuchFieldException("Field with name " + relationValue + " on class " + entity.getClass() + " not exist");
					}
					Object nestedEntity = ReflectionUtils.getFieldValue(tfield, entity);
					if(nestedEntity == null) {
						continue;
					}
					if(property == null) {
						//mapping class to class
						@SuppressWarnings("unchecked")
						Class<Object> nestedEntityClass = (Class<Object>) tfield.getType();
						@SuppressWarnings("unchecked")
						Class<Object> nestedWrapperClass = (Class<Object>) f.getType();
						AnnotationMappingConvertor<Object, Object> nestedConvertor = new AnnotationMappingConvertor<Object, Object>(nestedEntityClass, nestedWrapperClass);
						
						Object nestedWrapper = nestedConvertor.convertToWrapper(nestedEntity);
						ReflectionUtils.setValue(f.getName(), wrapper, nestedWrapper);
					} else {
						//mapping class to value
						Object mappedValue = ReflectionUtils.reflectValue(nestedEntity, property.value());
						ReflectionUtils.setValue(f.getName(), wrapper, mappedValue);
					}
				} else {
					String path = f.getName();
					if (property != null) {
						path = property.value();
					}
					Object value = ReflectionUtils.reflectValue(entity, path);
					ReflectionUtils.setValue(f.getName(), wrapper, value);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		Method afterMethod = this.findAnnotatedMethod(wclass, AfterWrapper.class);
		if(afterMethod != null) {
			ReflectionUtils.invokeMethod(afterMethod, wrapper);
		}
	}

	public Method findAnnotatedMethod(Class<?> cls, Class<? extends Annotation> ann) {
		for(Method m:cls.getDeclaredMethods()) {
			if(m.getDeclaredAnnotation(ann) != null) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public void updateEntity(E entity, W wrapper) {
		Class<W> wclass = wrapperClass;
		Method beforeMethod = this.findAnnotatedMethod(wclass, BeforeEntity.class);
		if(beforeMethod != null) {
			ReflectionUtils.invokeMethod(beforeMethod, wrapper);
		}
		for (Field f : wclass.getDeclaredFields()) {
			WrapperIgnore ignore = f.getDeclaredAnnotation(WrapperIgnore.class);
			if (ignore != null)
				continue;
			
			WrapperProperty property = f.getDeclaredAnnotation(WrapperProperty.class);
			WrapperRelation relation = f.getDeclaredAnnotation(WrapperRelation.class);
			try {
				if (relation != null) {
					String relationValue = relation.value();
					if(StringUtils.isEmpty(relationValue)) {
						relationValue = f.getName();
					}
					String idName = relation.idName();
					Field tfield = ReflectionUtils.findField(entity.getClass(), relationValue);
					@SuppressWarnings("unchecked")
					Class<Object> nestedEntityClass = (Class<Object>) tfield.getType();
					FinderService<? extends Object> service = this.createFinderInstance(relation.service(), nestedEntityClass);
					
					if(property == null) {
						Object nestedWrapper = ReflectionUtils.getFieldValue(f, wrapper);
						if (nestedWrapper == null) {
							continue; 
						}
						Object mappedValue = ReflectionUtils.reflectValue(nestedWrapper, idName);
						Object nestedEntity = service.findById(mappedValue);
						ReflectionUtils.setValue(relationValue, entity, nestedEntity);
						if(relation.allowNestedInjection()) {
							
							@SuppressWarnings("unchecked")
							Class<Object> nestedWrapperClass = (Class<Object>) f.getType();
							AnnotationMappingConvertor<Object, Object> nestedConvertor = new AnnotationMappingConvertor<>(nestedEntityClass, nestedWrapperClass);
							nestedConvertor.updateEntity(nestedEntity, nestedWrapper);
						}
					} else {
						Object mappedValue = ReflectionUtils.reflectValue(wrapper, f.getName());
						Object nestedEntity = service.findById(mappedValue);
						ReflectionUtils.setValue(relationValue, entity, nestedEntity);
					}
				} else {
					String path = f.getName();
					if (property != null) {
						path = property.value();
					}
					Object value = ReflectionUtils.getFieldValue(f, wrapper);
					ReflectionUtils.setNestedValue(path, entity, value);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		Method afterMethod = this.findAnnotatedMethod(wclass, AfterEntity.class);
		if(afterMethod != null) {
			Class<?>[] paramTypes = afterMethod.getParameterTypes();
			Object[] params = new Object[paramTypes.length];
			for(int i = 0; i < paramTypes.length; i++) {
				Class<?> type = paramTypes[i];
				if(type.equals(entityClass)) {
					params[i] = entity;
				}
			}
			ReflectionUtils.invokeMethod(afterMethod, wrapper, params);
		}
	}

	@SuppressWarnings("unchecked")
	private FinderService<? extends Object> createFinderInstance(Class<? extends FinderService<? extends Object>> sclass, Class<Object> argument) throws InstantiationException, IllegalAccessException {
		try {
			Constructor<?> c = sclass.getDeclaredConstructor(argument.getClass());
			return (FinderService<? extends Object>) c.newInstance(argument);
		} catch (Exception e) {
			log.warn("No constructor found with argument of type " + argument.getClass() + " of class " + sclass);
		}
		return sclass.newInstance();
	}
	
	@Override
	public E convertToEntity(W wrapper) throws WrapperConversionException {
		try {
			Class<E> eclass = entityClass;
			E entity = eclass.newInstance();
			this.updateEntity(entity, wrapper);
			return entity;
		} catch (Exception e) {
			throw new WrapperConversionException(e);
		}
	}

	@Override
	public W convertToWrapper(E entity) throws WrapperConversionException {
		try {
			Class<W> wclass = wrapperClass;
			W wrapper = wclass.newInstance();
			this.updateWrapper(entity, wrapper);
			return wrapper;
		} catch (Exception e) {
			throw new WrapperConversionException(e);
		}
	}

}
