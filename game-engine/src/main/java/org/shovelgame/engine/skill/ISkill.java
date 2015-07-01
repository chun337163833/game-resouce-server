package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.FightingMinion;

public interface ISkill {
	
	SkillResult process(FightingMinion source, FightingMinion target);

}
