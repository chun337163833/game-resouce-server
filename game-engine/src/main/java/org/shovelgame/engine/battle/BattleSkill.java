package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.shovelgame.engine.session.command.parameters.UseSkillParameters;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillType;
import org.shovelgame.game.domain.enumeration.TeamTarget;
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
	private Set<BattleItem> items = new HashSet<>();
	
	private Integer reuse = 0;
	
	@JsonIgnore
	private BattleMinion minion;
	
	private Map<String, MinionPosition[]> availablePositions;
	
	public BattleSkill(MinionSkill skill, BattleMinion minion) {
		super();
		this.minionSkill = skill;
		this.minion = minion;
		this.defaultPower = skill.getPower();
		this.currentPower = this.defaultPower;
		Skill s = skill.getSkill();
		this.cooldown = s.getCooldown();
		this.ticks = s.getTicks();
		this.type = s.getType();
		if(s.getAttributeType() != null)
		this.attribute = s.getAttributeType().getId();
	}

	private void updateByTraits(Set<BattleTrait> traits) {
		if (traits == null) {
			traits = new HashSet<>();
		}
		this.traits = traits;
		this.traits.forEach((BattleTrait t) -> update(t));		
	}
	
	private void updateByItems(Set<BattleItem> i) {
		if (items == null) {
			items = new HashSet<>();
		}
		i.forEach((BattleItem it) -> {
			if(it.hasSkillEnchantment()) {
				items.add(it);
				update(it);
			}
		});
	}
	
	
	
	public void update(Set<BattleTrait> traits, Set<BattleItem> items) {
		this.currentPower = this.defaultPower;
		this.updateByTraits(traits);
		this.updateByItems(items);
		this.resolveSkillAvailability();
	}

	private void update(BattleTrait trait) {
		BigDecimal addition = trait.getType().add(this.currentPower, trait.getPower());
		if (TraitAlgorithm.Increase.equals(trait.getTraitAlgorithm())) {
			this.currentPower = this.currentPower.add(addition);
		} else {
			this.currentPower = this.currentPower.subtract(addition);
		}
	}

	private void update(BattleItem item) {
		//this.currentPower = this.currentPower.add(ench.getValue());
		for(BattleEnchantment ench: item.getEnchantments()) {
			if(ench.getSkillId().equals(getSkillId())) {
				this.currentPower = this.currentPower.add(this.currentPower.multiply(ench.getValue()));
			}
		}
	}
	
	private void resolveSkillAvailability() {
		availablePositions = new HashMap<>();
		//TODO resolve skill availability
		BattleTeam ownerTeam = this.minion.getTeam();
		TeamTarget teamTarget = this.minionSkill.getSkill().getAlg().getTeamTarget();
		MinionPosition[] positions = this.minionSkill.getSkill().getAlg().getTargets();
		if(TeamTarget.Local.equals(teamTarget) || TeamTarget.Both.equals(teamTarget)) {
			Set<MinionPosition> pos = new HashSet<>();
			pos.addAll(Arrays.asList(positions));
			pos.remove(this.minion.getPosition());
			this.availablePositions.put(ownerTeam.getTeamId(), positions);
		} 
		if(TeamTarget.Enemy.equals(teamTarget) || TeamTarget.Both.equals(teamTarget)) {
			String teamId = ownerTeam.getOpponentTeamDelegate().getTeam().getTeamId();
			if(ownerTeam.getOpponentTeamDelegate().getTeam().getMinions().get(MinionPosition.Mid).isDied()) {
				//if target is enemy team and mid minion is dead then skill usage on leader is available
				Set<MinionPosition> pos = new HashSet<>();
				pos.addAll(Arrays.asList(positions));
				pos.add(MinionPosition.Leader);
				this.availablePositions.put(teamId, pos.toArray(new MinionPosition[pos.size()]));
			} else {
				this.availablePositions.put(teamId, positions);
			}
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
		if(this.reuse > 0) {
			return false;
		}
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
	public void setReuse() {
		this.reuse = this.cooldown;
	}
	public void newRound() {
		if(this.reuse > 0) {
			this.reuse--;
		}
	}
	public BattleMinion getMinion() {
		return minion;
	}
}
