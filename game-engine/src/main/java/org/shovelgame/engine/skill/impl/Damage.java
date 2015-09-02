package org.shovelgame.engine.skill.impl;

import java.math.BigDecimal;

import org.shovelgame.annotation.Logger;
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
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Damage)
@Logger
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
			res = processInternal(source, target);
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

	public SkillResult processInternal(BattleMinion source, BattleMinion target) {
		boolean positive = isPositive();
		SkillResult res = calculate(source, target, AttributeManagedType.Health, positive);
		Stat stat = target.getStat(AttributeManagedType.Health);
		if(positive) {
			BigDecimal value = stat.getCurrentValue().add(res.getResultValue());
			stat.changeValue(value);
			log.debug("target health " + value);
		} else {
			BigDecimal value = stat.getCurrentValue().subtract(res.getResultValue());
			stat.changeValue(value);
			log.debug("target health " + value);
		}
		return res;
	}
	
	public boolean isPositive() {
		return false;
	}
	
	@Override
	public SkillResult tick(BattleMinion target) {
		SkillResult result = this.processInternal(super.skill.getMinion(), target);
		result.setTick(true);
		UseSkillParameters params = new UseSkillParameters();
		params.setSkillId(getSourceId());
		params.setTarget(target.getPosition());
		params.setTargetTeam(target.getTeam().getTeamId());
		result.setMetadata(params);
		return result;
	}

}
