package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.skill.OvertimeEffect;
import org.shovelgame.engine.skill.TemporaryState;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.model.MinionAttribute;
import org.shovelgame.game.domain.model.MinionModel;
import org.shovelgame.game.domain.model.MinionSkill;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Logger
public class BattleMinion implements StatsOwnerDelegate {

	private Stat[] stats;
	private boolean died;
	
	/**
	 * Traits which affect this minion.
	 * This property serves for calculate stats and as information about affected traits for same client.
	 */
	private Set<BattleTrait> affectedTraits;
	
	private Set<BattleSkill> skills;
	private List<OvertimeEffect> effects;
	private List<TemporaryState> states;
	@JsonIgnore
	private BattleTeam team;

	public BattleMinion(BattleTeam team) {
		this.team = team;
		this.skills = new HashSet<>();
		this.effects = new ArrayList<>();
		this.states = new ArrayList<>();
	}
	
	public void build() {
		Minion minion = getMinion();
		MinionModel model = minion.getMinionModel();
		model.getMinionSkills().forEach((MinionSkill s) -> this.skills.add(new BattleSkill(s, this)));
		
		List<Stat> stats = new ArrayList<Stat>();
		typeBlock: for (AttributeManagedType t : AttributeManagedType.values()) {
			for (MinionAttribute attr : model.getMinionAttributes()) {
				if (t.equals(attr.getType().getId())) {
					stats.add(new Stat(t, attr.getValue(), this));
					continue typeBlock;
				}
			}
			stats.add(new Stat(t, new BigDecimal(0), this));
		}
		this.stats = stats.toArray(new Stat[stats.size()]);
	}
	
	/**
	 * Update traits for this minion and his skills.
	 */
	public void update() {
		MinionPosition position = getPosition();
		this.affectedTraits = this.team.findTraitsForStats(position);
		this.skills.forEach((BattleSkill s) -> 
		{	
			Set<BattleTrait> traits = this.team.findTraitsForSkill(position, s.getMinionSkill().getSkill().getAlg());
			Set<BattleItem> items = new HashSet<>(Arrays.asList(this.team.getItems()));
			s.update(traits, items);
		});
		if(log.isDebugEnabled()) {
			log.debug(String.format("MinonTraits[%s:%s]", position.name(), this.affectedTraits.size()));
		}
	}

	public Stat getStat(AttributeManagedType type) {
		for (Stat s : this.stats) {
			if (s.getType().equals(type)) {
				return s;
			}
		}
		throw new IllegalArgumentException(String.format(
				"Attribute %s not found", type.name()));
	}
	
	@Override
	public BattleMinion getOwner() {
		return this;
	}

	public Stat[] getStats() {
		return stats;
	}
	
	public Minion getMinion() {
		return this.team.getTeam().getMinionByPosition(getPosition());
	}

	public Set<BattleTrait> getAffectedTraits() {
		return affectedTraits;
	}
	
	public void died() {
		this.died = true;
		this.team.getInstance().getQueue().update();
		if(MinionPosition.Leader.equals(getPosition())) {
			this.team.getInstance().gameEnd(this.team.getOpponentTeamDelegate().getTeam());			
		}
	}

	public boolean isDied() {
		return died;
	}
	public BattleSkill findSkill(String skillId) {
        for (BattleSkill skill : this.skills) {
            if (skillId.equals(skill.getSkillId())) {
                return skill;
            }
        }
        return null;
    }
	
	public BattleTeam getTeam() {
		return team;
	}
	
	public MinionPosition getPosition() {
		return this.team.getPositionForMinion(this);
	}
	public Set<BattleSkill> getSkills() {
		return skills;
	}
	public List<OvertimeEffect> getEffects() {
		return effects;
	}
	public List<TemporaryState> getStates() {
		return states;
	}
}
