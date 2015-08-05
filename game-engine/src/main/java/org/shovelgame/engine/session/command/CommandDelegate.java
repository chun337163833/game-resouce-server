package org.shovelgame.engine.session.command;

import org.shovelgame.engine.io.ClientDelegate;

public interface CommandDelegate {
	void received(Command command, ClientDelegate from) throws CommandException;
}
