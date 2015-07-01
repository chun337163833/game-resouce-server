package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.shovelgame.game.domain.enumeration.TraitAlgorithm;
import org.shovelgame.game.domain.model.MinionSkill;
import org.shovelgame.game.domain.model.MinionTrait;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleSkill {

	@JsonIgnore
	private MinionSkill minionSkill;
	private BigDecimal defaultPower;
	private BigDecimal currentPower;
	
	private Set<BattleTrait> traits = new HashSet<>();

	public BattleSkill(MinionSkill skill) {
		super();
		this.minionSkill = skill;
		this.defaultPower = skill.getPower();
		this.currentPower = this.defaultPower;
	}

	public void update(Set<BattleTrait> traits) {
		if(traits == null) {
			traits = new HashSet<>();
		}
		this.traits = traits;
		this.traits.forEach((BattleTrait t) -> update(t));
	}
	
	private void update(BattleTrait trait) {
		BigDecimal addition = trait.getType().add(this.defaultPower, trait.getPower());
		if(TraitAlgorithm.Increase.equals(trait.getTraitAlgorithm())) {
			this.currentPower = this.defaultPower.add(addition);
		} else {
			this.currentPower = this.defaultPower.subtract(addition);
		}
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
	
	@Override
	public String toString() {
		return String.format("Skill[%s] -> traits[%d]", getSkillId(), traits.size());
	}
	
}
