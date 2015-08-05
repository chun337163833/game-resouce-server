package org.shovelgame.engine.io;

import org.shovelgame.engine.session.command.Command;


public interface ClientConnection {
	public void send(Command command) throws ClientStreamException;
	public void sendError(Command unproceseedCommand, String message) throws ClientStreamException;
}
