package org.shovelgame.game.domain.leveling;


public class LevelingServiceAccessor {

	private LevelingService service;
	
	public void setService(LevelingService service) {
		this.service = service;
	}
	private static LevelingServiceAccessor instance;
	public LevelingServiceAccessor() {
		instance = this;
	}
	public static LevelingService getLevelingService() {
		return instance.service;
	}

}
