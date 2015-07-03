package org.shovelgame.engine.session.communication;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Team;

public class AICommunicator extends Communicator {
	private Communicator comm;
	public AICommunicator(Team team) {
		super(team);
	}

	@Override
	public boolean equals(ClientConnection conection) {
		return false;
	}

	/**
	 * In AI this method serves as receiver
	 */
	@Override
	public void send(Command command) {
		

	}

}
