package com.tmobile.wrapper.conversion;

import org.junit.Assert;
import org.junit.Test;
import org.shovelgame.web.spring.ws.wrapper.convertor.AnnotationMappingConvertor;

import com.tmobile.wrapper.conversion.entity.EntityA;
import com.tmobile.wrapper.conversion.entity.NestedEntityFinder;
import com.tmobile.wrapper.conversion.wrapper.WrapperA;

public class ConversionTest {


	@Test
	public void doTest() {
		EntityA master = new EntityA();
		master.setName("hello");
		master.setEntity(new NestedEntityFinder().findById(1L));
		master.setEntity2(new NestedEntityFinder().findById(2L));
		master.setEntity3(new NestedEntityFinder().findById(3L));

		AnnotationMappingConvertor<EntityA, WrapperA> c = new AnnotationMappingConvertor<>(EntityA.class, WrapperA.class);
		WrapperA wrapper = c.convertToWrapper(master);
		Assert.assertEquals(wrapper.getEntity().getId(), Long.valueOf(1L));
		Assert.assertEquals(wrapper.getEntity2().getId(), Long.valueOf(2L));
		Assert.assertEquals(wrapper.getEntity3(), Long.valueOf(3L));

		// switch data and do some changes
		wrapper.getEntity().setId(3L);
		wrapper.getEntity().setName("zmena ktera se projevi");
		wrapper.getEntity2().setId(1L);
		wrapper.getEntity2().setName("zmena ktera se neprojevi");
		wrapper.setEntity3(2L);
		master = c.convertToEntity(wrapper);
		Assert.assertEquals(master.getEntity().getId(), Long.valueOf(3L));
		Assert.assertEquals(master.getEntity2().getId(), Long.valueOf(1L));
		Assert.assertEquals(master.getEntity3().getId(), Long.valueOf(2L));
		Assert.assertNotEquals(master.getEntity2().getName(), wrapper.getEntity2().getName());
		Assert.assertEquals(master.getEntity().getName(), wrapper.getEntity().getName());

	}

}
