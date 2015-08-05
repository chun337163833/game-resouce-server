package org.shovelgame.game.domain.leveling;

public class LevelingServiceAccessor {

	private LevelingServiceProvider provider;

	public void setProvider(LevelingServiceProvider provider) {
		this.provider = provider;
	}

	private static LevelingServiceAccessor instance;

	public LevelingServiceAccessor() {
		instance = this;
	}

	public static LevelingService getLevelingService(String name) {
		return instance.provider.getService(name);
	}

}
