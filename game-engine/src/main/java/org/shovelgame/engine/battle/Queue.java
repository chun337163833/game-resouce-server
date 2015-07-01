package org.shovelgame.engine.battle;

import org.shovelgame.game.domain.enumeration.MinionPosition;

public class Queue {
	
	private FightingTeam team1;
	private FightingTeam team2;
	public Queue(FightingTeam team1, FightingTeam team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public FightingMinion getCurrent() {
		return this.team1.getMinions().get(MinionPosition.Top);
	}
	
}
