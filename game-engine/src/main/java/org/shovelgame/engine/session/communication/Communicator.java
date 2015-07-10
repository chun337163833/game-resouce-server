package org.shovelgame.engine.session.communication;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public abstract class Communicator {

	private Team team;
	private ClientConnection connection;
	public Communicator(Team team) {
		this(team, null);
	}
	public Communicator(Team team, ClientConnection connection) {
		super();
		this.connection = connection;
		this.team = team;
		Assert.notNull(team, "Team cannot be null.");
	}

	public abstract void send(Command command);

	public abstract boolean equals(ClientConnection conection);

	public Team getTeam() {
		return team;
	}

	public ClientConnection getConnection() {
		return connection;
	}
}
