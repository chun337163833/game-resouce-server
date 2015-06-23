package org.shovelgame.engine.session.communication;

import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public abstract class Communicator {

	private Team team;

	public Communicator(Team team) {
		super();
		this.team = team;
		Assert.notNull(team, "Team cannot be null.");
	}
	
	public abstract void send(Command command) throws ClientStreamException;
	
	public Team getTeam() {
		return team;
	}
}
