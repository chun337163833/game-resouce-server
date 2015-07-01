package org.shovelgame.engine.battle;

import org.shovelgame.game.domain.enumeration.MinionPosition;

public class Queue {
	
	private BattleTeam team1;
	private BattleTeam team2;
	public Queue(BattleTeam team1, BattleTeam team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public BattleMinion getCurrent() {
		return this.team1.getMinions().get(MinionPosition.Leader);
	}
	
}
