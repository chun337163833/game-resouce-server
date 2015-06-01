package org.shovelgame.engine.session.communication;

import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public abstract class Communication {

	private Team team;

	public Communication(Team team) {
		super();
		this.team = team;
		Assert.notNull(team, "Team cannot be null.");
	}

	public Team getTeam() {
		return team;
	}
}
