package org.shovelgame.game.domain.leveling;


public interface LevelingServiceProvider {

	LevelingService getService(String name);
}
