package org.shovelgame.engine.skill.impl;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Damage)
public class Damage extends AbstractSkill {
	
	public Damage(BattleSkill skill) {
		super(skill);
	}

	@Override
	public SkillResult process(BattleMinion source, BattleMinion target) {
		SkillResult res = new SkillResult();
//		BigDecimal skillpower = skill.getPower();
		return res;
	}
}
