package org.shovelgame.game.domain.leveling;

public aspect LevelableAspect {
	declare parents: @Levelable * implements Level;
	after(): execution(* org.shovelgame.game.domain.leveling.Level.setExperience(Long)) {
		 Object target = thisJoinPoint.getTarget();
		 if(target instanceof Level) {
			 Level level = (Level) target;
			 LevelingServiceAccessor.getLevelingService().experienceChanged(level);
		 }
	}
}
