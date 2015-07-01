package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.model.MinionAttribute;
import org.shovelgame.game.domain.model.MinionModel;
import org.shovelgame.game.domain.model.MinionTrait;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Logger
public class FightingMinion implements StatsOwnerDelegate {

	private Stat[] stats;
	private MinionPosition position;
	private boolean died;
	/**
	 * All traits which affect this minion, from enemy team and this team
	 */
	@JsonIgnore
	private Set<MinionTrait> affectedTraits;

	@JsonIgnore
	private FightingTeam team;

	public FightingMinion(MinionPosition position, FightingTeam team) {
		this.position = position;
		this.team = team;
		Minion minion = getMinion();
		MinionModel model = minion.getMinionModel();
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
	public void initializeStats() {
		for(Stat stat: this.stats) {
			stat.initialize();
		}
	}
	public void updateTraits() {
		this.affectedTraits = this.team.findTraitsForPosition(this.position);
		if(log.isDebugEnabled()) {
			log.debug(String.format("MinonTraits[%s:%s]", position.name(), this.affectedTraits.size()));
		}
	}

	public Stat getStatValue(AttributeManagedType type) {
		for (Stat s : this.stats) {
			if (s.getType().equals(type)) {
				return s;
			}
		}
		throw new IllegalArgumentException(String.format(
				"Attribute %s not found", type.name()));
	}
	
	@Override
	public FightingMinion getOwner() {
		return this;
	}

	public Stat[] getStats() {
		return stats;
	}
	
	public Minion getMinion() {
		return this.team.getTeam().getMinionByPosition(this.position);
	}

	public MinionPosition getPosition() {
		return position;
	}

	public Set<MinionTrait> getAffectedTraits() {
		return affectedTraits;
	}
	
	public void died() {
		this.died = true;
		if(MinionPosition.Leader.equals(this.position)) {
			this.team.getInstance().gameEnd(this.team.getOpponentTeamDelegate().getTeam());			
		} else {
			this.team.getInstance().update();
		}
	}

	public boolean isDied() {
		return died;
	}
}
