package com.nex.wrapper.conversion.entity;

import java.util.ArrayList;
import java.util.List;

import com.nex.web.spring.ws.wrapper.FinderService;

public class NestedEntityFinder implements FinderService<NestedEntity> {
	public static List<NestedEntity> dbSimluator = new ArrayList<>();

	Class<NestedEntity> cls;

	public NestedEntityFinder() {
		// TODO Auto-generated constructor stub
	}
	
	public NestedEntityFinder(Class<NestedEntity> cls) {
		super();
		this.cls = cls;
	}

	public NestedEntityFinder(String myargument) {
		
	}
	
	static {
		NestedEntity e = new NestedEntity();
		e.setId(1L);
		e.setName("test");
		dbSimluator.add(e);

		e = new NestedEntity();
		e.setId(2L);
		e.setName("test2");
		dbSimluator.add(e);

		e = new NestedEntity();
		e.setId(3L);
		e.setName("test3");
		dbSimluator.add(e);
	}

	@Override
	public NestedEntity findById(Object id) {
		for (NestedEntity e : dbSimluator) {
			if (Long.valueOf(id.toString()).equals(e.getId())) {
				return e;
			}
		}
		return null;
	}
}
