package org.shovelgame.engine.session.command;

import org.shovelgame.engine.io.ClientConnection;

public interface CommandDelegate {
	void received(Command command, ClientConnection from) throws CommandException;
}
