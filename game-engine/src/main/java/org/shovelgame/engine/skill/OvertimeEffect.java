package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.BattleMinion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OvertimeEffect {

	private Integer remaining;
	
	@JsonIgnore
	private OvertimeSkill source;

	public Integer getRemaining() {
		return remaining;
	}
	public OvertimeEffect(OvertimeSkill source) {
		super();
		this.source = source;
		this.remaining = source.getDuration();
	}

	public SkillResult tick(BattleMinion target) {
		remaining--;
		return this.source.tick(target);
	}

	public boolean isExpired() {
		return remaining == 0;
	}

	@JsonProperty("skillId")
	public String getSourceSkillId() {
		return this.source.getSourceId();
	}
}
