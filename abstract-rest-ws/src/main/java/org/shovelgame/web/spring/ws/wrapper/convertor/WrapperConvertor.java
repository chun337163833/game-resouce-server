package org.shovelgame.web.spring.ws.wrapper.convertor;

import java.util.List;

public interface WrapperConvertor<E extends Object, W extends Object> {
	E convertToEntity(W wrapper) throws WrapperConversionException;
	List<E> convertListOfEntities(List<W> list) throws WrapperConversionException;
	
	void updateEntity(E entity, W wrapper);
	void updateWrapper(E entity, W wrapper);
	
	W convertToWrapper(E entity) throws WrapperConversionException;
	List<W> convertListOfWrappers(List<E> list) throws WrapperConversionException;
}
