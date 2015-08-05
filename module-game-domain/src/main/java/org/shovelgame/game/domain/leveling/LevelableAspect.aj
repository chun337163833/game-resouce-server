package org.shovelgame.game.domain.leveling;


public aspect LevelableAspect {
	declare parents: @Levelable * implements Level;

	public void Level.addExperience(Long l) {
		this.setExperience((long) ((l + this.getExperience()) + (l * this.getBoostExperience())));
	}

	after(): execution(* org.shovelgame.game.domain.leveling.Level.addExperience(Long)) {
		 Object target = thisJoinPoint.getTarget();
		 Levelable l = target.getClass().getAnnotation(Levelable.class);
		 if(target instanceof Level) {
			 Level level = (Level) target;
			 LevelingServiceAccessor.getLevelingService(l.service()).experienceChanged(level);
		 }
	}
}
