package org.shovelgame.engine.skill;

import java.math.BigDecimal;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TemporaryState {

	/**
	 * pozitive or negative value
	 */
	private BigDecimal value;
	private Integer remaining;
	
	@JsonIgnore
	private TemporarySkill source;

	public TemporaryState(TemporarySkill skill, BigDecimal value) {
		super();
		this.source = skill;
		this.value = value;
		this.remaining = skill.getDuration();
	}

	public BigDecimal getValue() {
		return value;
	}

	@JsonProperty("attribute")
	public AttributeManagedType getAttribute() {
		return this.source.getAttribute();
	}
	
	@JsonProperty("skillId")
	public String getSourceSkillId() {
		return this.source.getSourceId();
	}
	
	public Integer getRemaining() {
		return remaining;
	}
}
