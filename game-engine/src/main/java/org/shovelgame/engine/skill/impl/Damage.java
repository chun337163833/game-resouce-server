package org.shovelgame.engine.skill.impl;

import org.shovelgame.engine.battle.FightingMinion;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.shovelgame.game.domain.model.MinionSkill;

@Skill(SkillAlgorithm.Damage)
public class Damage extends AbstractSkill {

	public Damage(MinionSkill skill) {
		super(skill);
	}

	@Override
	public SkillResult process(FightingMinion source, FightingMinion target) {
		SkillResult res = new SkillResult();
		res.setValue(1L);
		return res;
	}
}
