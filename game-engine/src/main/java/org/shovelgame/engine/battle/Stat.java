package org.shovelgame.engine.battle;

import java.math.BigDecimal;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stat {

	private AttributeManagedType type;
	private BigDecimal value;
	
	@JsonIgnore
	private StatsAfflictionDelegate delegate;
	public Stat(AttributeManagedType type, BigDecimal value, StatsAfflictionDelegate delegate) {
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
	}

	@JsonProperty("realValue")
	public BigDecimal getRealValue() {
		return this.delegate.getValue(this);
	}
}
