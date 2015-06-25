package org.shovelgame.engine.battle;

import java.math.BigDecimal;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.model.MinionTrait;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stat {

	private AttributeManagedType type;
	private BigDecimal value;

	@JsonIgnore
	private StatsOwnerDelegate delegate;

	public Stat(AttributeManagedType type, BigDecimal value,
			StatsOwnerDelegate delegate) {
		super();
		this.type = type;
		this.value = value;
		this.delegate = delegate;
	}

	public AttributeManagedType getType() {
		return type;
	}

	public void setType(AttributeManagedType type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
		if(AttributeManagedType.Health.equals(getType()) && this.value.intValue() <= 0) {
			this.delegate.getOwner().died();
		}
	}

	@JsonProperty("realValue")
	public BigDecimal getRealValue() {
		FightingMinion minion = this.delegate.getOwner();
		// TODO get all trait, items and item enchantment what are eligible to
		// increase this stat and increase value
		BigDecimal value = getValue();
		for (MinionTrait trait : minion.getAffectedTraits()) {
			if (trait.getTrait().getAffectedAttributeType().equals(getType())) {

			}
		}
		return value;
	}

}
