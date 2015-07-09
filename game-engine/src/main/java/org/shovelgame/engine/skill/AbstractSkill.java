package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.battle.Stat;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.SkillType;

public abstract class AbstractSkill implements ISkill {
	protected BattleSkill skill;
	public AbstractSkill(BattleSkill skill) {
		super();
		this.skill = skill;
	}

	private boolean isPhysical() {
		return SkillType.PhysicalPower.equals(skill.getType());
	}
	
	public BigDecimal resolvePower(BattleMinion minion) {
		BigDecimal power = minion.getStat(AttributeManagedType.PhysicalPower).getCurrentValue();
		if(!isPhysical()) {
			power = minion.getStat(AttributeManagedType.SpellPower).getCurrentValue();
		}
		return power.add(power.multiply(skill.getCurrentPower()));
	}
	
	public BigDecimal resolveCriticalChance(BattleMinion source, BattleMinion target) {
		BigDecimal crit = source.getStat(AttributeManagedType.PhysicalCritChance).getCurrentValue();
		BigDecimal resist = target.getStat(AttributeManagedType.PhysicalCritResistance).getCurrentValue();
		if(!isPhysical()) {
			crit = source.getStat(AttributeManagedType.SpellCritChance).getCurrentValue();
			resist = target.getStat(AttributeManagedType.SpellCritResistance).getCurrentValue();
		}
		return toZeroIfNegative(crit.subtract(resist));
	}
	
	public BigDecimal resolveCriticalDamage(BattleMinion minion) {
		return minion.getStat(AttributeManagedType.CriticalDamage).getCurrentValue();
	}
	
	protected SkillResult calculate(BattleMinion source, BattleMinion target, AttributeManagedType type, boolean isPositive) {
		SkillResult res = new SkillResult();
		res.setCritical(ChanceEvaluator.success(resolveCriticalChance(source, target)));
		BigDecimal power = resolvePower(source);
		BigDecimal critDmg = resolveCriticalDamage(source);
		BigDecimal resistance = resolveResistance(source, target);
		if(res.isCritical()) {
			power = power.add(power.multiply(critDmg));
		}
		if(!isPositive) {
			res.setResistedValue(power.multiply(resistance));
			res.setResultValue(power.subtract(res.getResistedValue()));
		} else {
			res.setResistedValue(BigDecimal.valueOf(0));
			res.setResultValue(power);
		}
		return res;
	}
	
	public BigDecimal resolveResistance(BattleMinion attacker, BattleMinion defender) {
		BigDecimal resistance = defender.getStat(AttributeManagedType.PhysicalResistance).getCurrentValue();
		BigDecimal penetration = attacker.getStat(AttributeManagedType.PhysicalPenetration).getCurrentValue();
		if(!isPhysical()) {
			resistance = defender.getStat(AttributeManagedType.SpellResistance).getCurrentValue();
			penetration = attacker.getStat(AttributeManagedType.SpellPenetration).getCurrentValue();
		}
		return toZeroIfNegative(resistance.subtract(penetration));
	}

	public BigDecimal toZeroIfNegative(BigDecimal b) {
		if(b.signum() == -1) {
			return new BigDecimal(0);
		}
		return b;
	}
	
}
