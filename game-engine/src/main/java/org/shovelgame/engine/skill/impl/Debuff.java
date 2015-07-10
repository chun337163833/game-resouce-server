package org.shovelgame.engine.skill.impl;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
import org.shovelgame.engine.skill.TemporarySkill;
import org.shovelgame.engine.skill.TemporaryState;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;

public class Debuff extends AbstractSkill implements TemporarySkill {

	public Debuff(BattleSkill skill) {
		super(skill);
	}

	@Override
	public String getSourceId() {
		return skill.getSkillId();
	}

	@Override
	public Integer getDuration() {
		return skill.getTicks();
	}

	@Override
	public SkillResult process(BattleMinion source, BattleMinion target)
			throws SkillUsageException {
		SkillResult result = processInternal(source, target);
		target.getStates().add(new TemporaryState(this, result.getResultValue()));
		return result;
	}
	
	public boolean isPositive() {
		return false;
	}
	
	public SkillResult processInternal(BattleMinion source, BattleMinion target) {
		boolean positive = isPositive();
		SkillResult result = calculate(source, target, skill.getAttribute(), positive);
		if(!positive) {
			//negate value if is debuff
			result.setResultValue(result.getResultValue().multiply(BigDecimal.valueOf(-1)));
		}
		if(result.getResultValue().intValue() == 0) {
			result.setResisted(true);
		}
		result.setTemporary(true);
		return result;
	}
	
	@Override
	public AttributeManagedType getAttribute() {
		return skill.getAttribute();
	}


}
