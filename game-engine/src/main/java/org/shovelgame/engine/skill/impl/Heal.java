package org.shovelgame.engine.skill.impl;

import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Heal)
public class Heal extends Damage {

	public Heal(BattleSkill skill) {
		super(skill);
	}

	@Override
	public boolean isPositive() {
		return true;
	}
}
