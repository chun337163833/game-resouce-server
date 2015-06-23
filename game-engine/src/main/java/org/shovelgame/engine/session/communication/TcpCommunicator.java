package org.shovelgame.engine.session.communication;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.Command;

public class TcpCommunicator extends Communicator {

	ClientConnection connection;

	public TcpCommunicator(ClientConnection connection) {
		super(connection.getPlayer().getTeam());
		this.connection = connection;
	}

	@Override
	public void send(Command command) throws ClientStreamException {
		this.connection.send(command);
	}
}
