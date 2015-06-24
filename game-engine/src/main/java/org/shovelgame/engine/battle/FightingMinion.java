package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.model.MinionAttribute;
import org.shovelgame.game.domain.model.MinionModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FightingMinion implements StatsAfflictionDelegate {

	@JsonIgnore
	private Minion minion;
	private Stat[] maxStats;
	private Stat[] currStats;

	public FightingMinion() {
		// TODO Auto-generated constructor stub
	}

	public FightingMinion(Minion minion, FightingTeam team) {
		this.minion = minion;
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
		this.maxStats = stats.toArray(new Stat[stats.size()]);
		// add traits and level and items
		this.currStats = stats.toArray(new Stat[stats.size()]);
	}

	public void update() {

	}

	public BigDecimal getMaxStatValue(AttributeManagedType type) {
		return getStatValue(type, this.maxStats);
	}

	public BigDecimal getCurrStatValue(AttributeManagedType type) {
		return getStatValue(type, this.currStats);
	}

	public BigDecimal getStatValue(AttributeManagedType type, Stat[] array) {
		for (Stat s : array) {
			if (s.getType().equals(type)) {
				return s.getValue();
			}
		}
		throw new IllegalArgumentException(String.format(
				"Attribute %s not found", type.name()));
	}

	@Override
	public BigDecimal getValue(Stat stat) {
		return stat.getValue();
	}

	public Stat[] getMaxStats() {
		return maxStats;
	}

	public Stat[] getCurrStats() {
		return currStats;
	}
}
