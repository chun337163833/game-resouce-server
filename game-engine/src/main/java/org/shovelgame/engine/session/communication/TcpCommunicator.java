package org.shovelgame.engine.session.communication;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.io.PlayerConnection;
import org.shovelgame.engine.session.command.Command;
@Logger
public class TcpCommunicator extends Communicator {

	ClientConnection connection;
	public TcpCommunicator(PlayerConnection connection) {
		super(connection.getPlayer().getTeam(), connection);
		this.connection = connection;
	}
	@Override
	public boolean equals(ClientConnection connection) {
		return this.connection.equals(connection);
	}
	@Override
	public void send(Command command) {
		try {
			this.connection.send(command);
		} catch (ClientStreamException e) {
			log.error("", e);
		}
	}
}
