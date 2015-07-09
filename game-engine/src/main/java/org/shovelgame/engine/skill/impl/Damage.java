package org.shovelgame.engine.skill.impl;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.battle.Stat;
import org.shovelgame.engine.session.command.parameters.UseSkillParameters;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.OvertimeEffect;
import org.shovelgame.engine.skill.OvertimeSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Damage)
public class Damage extends AbstractSkill implements OvertimeSkill {
	
	public Damage(BattleSkill skill) {
		super(skill);
	}

	@Override
	public SkillResult process(BattleMinion source, BattleMinion target) throws SkillUsageException {
		boolean isDot = super.skill.getTicks() > 0;
		SkillResult res = new SkillResult();
		if(isDot) {
			res.setOvertime(true);
			target.getEffects().add(new OvertimeEffect(this));
		} else {
			res = calculate(source, target);
		}
		return res;
	}

	@Override
	public String getSourceId() {
		return super.skill.getSkillId();
	}

	@Override
	public Integer getDuration() {
		return super.skill.getTicks();
	}

	private SkillResult calculate(BattleMinion source, BattleMinion target) {
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
	
	@Override
	public SkillResult tick(BattleMinion target) {
		SkillResult result = this.calculate(super.skill.getMinion(), target);
		result.setTick(true);
		UseSkillParameters params = new UseSkillParameters();
		params.setSkillId(getSourceId());
		params.setSource(target.getPosition());
		params.setTeamId(target.getTeam().getTeamId());
		return result;
	}

}
