package org.shovelgame.web.spring.ws.wrapper.convertor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityConvertor<E extends Object, W extends Object>
		implements WrapperConvertor<E, W> {

	@Override
	public List<E> convertListOfEntities(List<W> list)
			throws WrapperConversionException {
		List<E> l = new ArrayList<>();
		for (W d : list) {
			l.add(this.convertToEntity(d));
		}
		return l;
	}

	@Override
	public List<W> convertListOfWrappers(List<E> list)
			throws WrapperConversionException {
		List<W> l = new ArrayList<>();
		for (E d : list) {
			l.add(this.convertToWrapper(d));
		}
		return l;
	}

}
