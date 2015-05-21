package org.shovelgame.engine.session;

import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public class BattleSession {

	private Team team1;
	private Team team2;
	
	public BattleSession(Team team1, Team team2) {
		super();
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = team1;
		this.team2 = team2;
	}

}
