package org.shovelgame.engine.skill.impl;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.battle.Stat;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Damage)
public class Damage extends AbstractSkill {
	
	public Damage(BattleSkill skill) {
		super(skill);
	}

	@Override
	public SkillResult process(BattleMinion source, BattleMinion target) throws SkillUsageException {
		SkillResult res = new SkillResult();
		res.setCritical(ChanceEvaluator.success(resolveCriticalChance(source, target)));
		BigDecimal power = resolvePower(source);
		BigDecimal critDmg = resolveCriticalDamage(source);
		BigDecimal resistance = resolveResistance(source, target);
		if(res.isCritical()) {
			power = power.add(power.multiply(critDmg));
		}
		res.setResistedValue(power.multiply(resistance));
		res.setResultValue(power.subtract(res.getResistedValue()));
		Stat targetHealth = target.getStat(AttributeManagedType.Health);
		BigDecimal health = targetHealth.getCurrentValue().subtract(res.getResultValue());
		targetHealth.changeValue(health);
		return res;
	}

}
