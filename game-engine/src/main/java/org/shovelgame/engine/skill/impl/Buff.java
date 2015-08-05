package org.shovelgame.engine.skill.impl;

import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Buff)
public class Buff extends Debuff {

	public Buff(BattleSkill skill) {
		super(skill);
	}
	
	@Override
	public boolean isPositive() {
		return true;
	}
	
}
