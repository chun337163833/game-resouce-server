package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.shovelgame.engine.collection.Valuable;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.shovelgame.game.domain.enumeration.TraitAlgorithm;
import org.shovelgame.game.domain.enumeration.TraitType;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.TraitTarget;
import org.springframework.roo.addon.equals.RooEquals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@RooEquals(excludeFields={"minionTrait", "targets", "owner"})
public class BattleTrait implements Valuable {

	@JsonIgnore
	private MinionTrait minionTrait;
	private String traitId;
	
	@JsonIgnore
	private BattleMinion owner;
	
	private MinionPosition[] targets;
	public BattleTrait(MinionTrait minionTrait, BattleMinion owner) {
		super();
		this.minionTrait = minionTrait;
		this.owner = owner;
		Set<MinionPosition> positions = new HashSet<>();
		minionTrait.getTraitTargets().forEach((TraitTarget t) -> positions.add(t.getPosition()));
		this.targets = positions.toArray(new MinionPosition[positions.size()]);
		this.traitId = minionTrait.getTrait().getTraitId();
	}

	public MinionTrait getMinionTrait() {
		return minionTrait;
	}
	@JsonProperty("skill")
	public SkillAlgorithm getSkillAlgorithm() {
		return this.minionTrait.getTrait().getAffectedSkillAlg();
	}
	@JsonProperty("type")
	public TraitType getType() {
		return this.minionTrait.getTrait().getType();
	}
	@JsonProperty("alg")
	public TraitAlgorithm getTraitAlgorithm() {
		return this.minionTrait.getTrait().getAlg();
	}
	@JsonProperty("attribute")
	public AttributeManagedType getAttribute() {
		if(this.minionTrait.getTrait().getAffectedAttributeType() == null) {
			return null;
		}
		return this.minionTrait.getTrait().getAffectedAttributeType().getId();
	}
	public String getTraitId() {
		return traitId;
	}
	@JsonProperty("power")
	public BigDecimal getPower() {
		return this.minionTrait.getPower();
	}
	
	@JsonProperty("source")
	public MinionPosition getSource() {
		return this.owner.getPosition();
	}
	
	@Override
	public BigDecimal getValue() {
		return getPower();
	}
	public MinionPosition[] getTargets() {
		return targets;
	}
	@Override
	public String toString() {
		return String.format("Trait[%s,%s,%s]", getTraitId(), getType(), getAttribute() == null? "Stat": "Skill");
	}
}
