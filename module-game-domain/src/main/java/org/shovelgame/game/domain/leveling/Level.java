package org.shovelgame.game.domain.leveling;

public interface Level {
	int MAX = 30;
	void setExperience(Long exp);
	void addExperience(Long exp);
	void setLevel(Integer level);
	Long getExperience();
	Integer getLevel();
	double getBoostExperience();
}
