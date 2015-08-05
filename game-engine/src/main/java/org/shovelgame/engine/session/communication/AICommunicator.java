package org.shovelgame.engine.session.communication;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.ai.AI;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.model.Mission;

@Logger
public class AICommunicator extends Communicator {
	private Mission mission;
	private AI ai;

	public AICommunicator(Mission mission) {
		super(mission.getTeam());
		this.mission = mission;
	}

	@Override
	public boolean equals(ClientConnection conection) {
		return false;
	}

	@Override
	public ClientConnection getConnection() {
		return this.ai;
	}

	/**
	 * In AI this method serves as receiver
	 */
	@Override
	public void send(Command command) {
		try {
			this.ai.received(command);
		} catch (ClientStreamException e) {
			log.error("", e);
		}
	}

	public void setAi(AI ai) {
		this.ai = ai;
	}

	public Mission getMission() {
		return mission;
	}
}
