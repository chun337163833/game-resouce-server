package org.shovelgame.engine.battle;

import java.math.BigDecimal;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.TraitAlgorithm;
import org.shovelgame.game.domain.model.MinionTrait;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stat {

	private AttributeManagedType type;
	private BigDecimal maxValue;
	private BigDecimal defaultValue;
	private BigDecimal currentValue;

	@JsonIgnore
	private StatsOwnerDelegate delegate;

	public Stat(AttributeManagedType type, BigDecimal value, StatsOwnerDelegate delegate) {
		super();
		this.type = type;
		this.delegate = delegate;
		this.defaultValue = value;
		this.maxValue = value;
	}

	public AttributeManagedType getType() {
		return type;
	}
	public void recalculate() {
		this.maxValue = getRealValue();
		if(this.currentValue == null) {
			this.currentValue = this.maxValue;
		}
		if(this.maxValue.doubleValue() < this.currentValue.doubleValue()) {
			this.currentValue = BigDecimal.valueOf(this.maxValue.doubleValue());
		}
	}

	public BigDecimal getRealValue() {
		BattleMinion minion = this.delegate.getOwner();
		// TODO get all trait, items and item enchantment what are eligible to 
		// increase this stat and increase value
		BigDecimal value = this.defaultValue;
		for (BattleTrait trait : minion.getAffectedTraits()) {
			AttributeManagedType type = trait.getAttribute();
			if (type.equals(getType())) {
				BigDecimal calculatedValue = trait.getType().add(value, trait.getPower());
				if (TraitAlgorithm.Increase.equals(trait.getTraitAlgorithm())) {
					value = value.add(calculatedValue);
				} else {
					value = value.subtract(calculatedValue);
				}
			}
		}
		return value;
	}

	public void changeValue(BigDecimal value) {
		this.currentValue = value;
		this.recalculate();
		if(AttributeManagedType.Health.equals(getType()) && this.currentValue.intValue() <= 0) {
			this.delegate.getOwner().died();
		}
	}
	
	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}
}
