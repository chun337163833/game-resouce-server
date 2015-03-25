package com.tmobile.test;

import org.junit.Test;
import org.shovelgame.web.menu.MenuFactory;

public class MenuFactoryTests {

	@Test
	public void testFactory() throws Exception {
		MenuFactory factory = new MenuFactory();
		factory.setScanPackage("com.tmobile.test.controller");
		factory.scan();
		
	}

}
