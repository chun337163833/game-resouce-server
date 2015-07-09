package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
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
		return power.multiply(skill.getCurrentPower());
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
