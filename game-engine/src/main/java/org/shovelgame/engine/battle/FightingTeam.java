package org.shovelgame.engine.battle;

import java.util.Set;

import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Team;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FightingTeam {

	private FightingMinion leader;
	private FightingMinion bot;
	private FightingMinion mid;
	private FightingMinion top;

	@JsonIgnore
	private Set<Item> items;

	public FightingTeam(Team team) {
		this.items = team.getItems();
		this.leader = new FightingMinion(team.getLeader(), this);
		this.bot = new FightingMinion(team.getMinionBot(), this);
		this.mid = new FightingMinion(team.getMinionMid(), this);
		this.top = new FightingMinion(team.getMinionTop(), this);

	}

	public FightingMinion getLeader() {
		return leader;
	}

	public FightingMinion getBot() {
		return bot;
	}

	public FightingMinion getMid() {
		return mid;
	}

	public FightingMinion getTop() {
		return top;
	}

	public Set<Item> getItems() {
		return items;
	}

}
