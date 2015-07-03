package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.shovelgame.engine.session.command.parameters.UseSkillParameters;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillType;
import org.shovelgame.game.domain.enumeration.TraitAlgorithm;
import org.shovelgame.game.domain.model.MinionSkill;
import org.shovelgame.game.domain.model.Skill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleSkill {

	@JsonIgnore
	private MinionSkill minionSkill;
	private BigDecimal defaultPower;
	private BigDecimal currentPower;
	private Integer cooldown;
	private Integer ticks;
	private SkillType type;
	private AttributeManagedType attribute;
	private Set<BattleTrait> traits = new HashSet<>();
	@JsonIgnore
	private BattleTeam ownerTeam;
	
	private Map<String, MinionPosition[]> availablePositions;
	
	public BattleSkill(MinionSkill skill, BattleTeam team) {
		super();
		this.minionSkill = skill;
		this.ownerTeam = team;
		this.defaultPower = skill.getPower();
		this.currentPower = this.defaultPower;
		Skill s = skill.getSkill();
		this.cooldown = s.getCooldown();
		this.ticks = s.getTicks();
		this.type = s.getType();
		this.attribute = s.getAttributeType().getId();
	}

	public void update(Set<BattleTrait> traits) {
		if (traits == null) {
			traits = new HashSet<>();
		}
		this.traits = traits;
		this.traits.forEach((BattleTrait t) -> update(t));
		this.resolveSkillAvailability();
	}

	private void update(BattleTrait trait) {
		BigDecimal addition = trait.getType().add(this.defaultPower, trait.getPower());
		if (TraitAlgorithm.Increase.equals(trait.getTraitAlgorithm())) {
			this.currentPower = this.defaultPower.add(addition);
		} else {
			this.currentPower = this.defaultPower.subtract(addition);
		}
	}

	private void resolveSkillAvailability() {
		availablePositions = new HashMap<>();
		//TODO resolve skill availability
	}
	
	public MinionSkill getMinionSkill() {
		return minionSkill;
	}

	public Set<BattleTrait> getTraits() {
		return traits;
	}

	@JsonProperty("skillId")
	public String getSkillId() {
		return this.getMinionSkill().getSkill().getSkillId();
	}

	public BigDecimal getDefaultPower() {
		return defaultPower;
	}

	public BigDecimal getCurrentPower() {
		return currentPower;
	}

	public Integer getCooldown() {
		return cooldown;
	}

	public Integer getTicks() {
		return ticks;
	}

	public SkillType getType() {
		return type;
	}

	public AttributeManagedType getAttribute() {
		return attribute;
	}

	public boolean canUse(UseSkillParameters params) {
		MinionPosition[] positions = this.availablePositions.get(params.getTeamId());
		if(positions != null) {
			for(MinionPosition pos: positions) {
				if(pos.equals(params.getTarget())) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("Skill[%s] -> traits[%d]", getSkillId(),
				traits.size());
	}

}
