package org.shovelgame.engine.session.command;

import org.shovelgame.engine.io.ClientDelegate;


public interface CommandProcessor {
	void process(Command command, ClientDelegate delegate) throws CommandException;
}
