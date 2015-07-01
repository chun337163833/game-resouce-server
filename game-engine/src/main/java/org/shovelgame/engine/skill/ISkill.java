package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.BattleMinion;

public interface ISkill {
	
	SkillResult process(BattleMinion source, BattleMinion target) throws SkillUsageException;

}
