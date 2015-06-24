package org.shovelgame.engine.battle;

import org.shovelgame.engine.session.command.BigData;
import org.shovelgame.game.domain.data.Team;

public class Battleground implements BigData {

	private FightingTeam team1;
	private FightingTeam team2;

	public Battleground(Team team1, Team team2) {
		super();
		this.team1 = new FightingTeam(team1);
		this.team2 = new FightingTeam(team1);
	}

	public FightingTeam getTeam1() {
		return team1;
	}


	public FightingTeam getTeam2() {
		return team2;
	}
	
}
