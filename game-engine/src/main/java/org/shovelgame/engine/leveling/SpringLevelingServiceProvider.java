package org.shovelgame.engine.leveling;

import org.shovelgame.game.domain.leveling.LevelingService;
import org.shovelgame.game.domain.leveling.LevelingServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SpringLevelingServiceProvider implements LevelingServiceProvider {

	@Autowired
	ApplicationContext context;
	
	@Override
	public LevelingService getService(String name) {
		return context.getBean(name, LevelingService.class);
	}

}
