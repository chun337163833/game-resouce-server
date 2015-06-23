package org.shovelgame.engine.session.command;

import org.shovelgame.engine.io.ServerDelegate;


public interface CommandProcessor {
	void process(Command command, ServerDelegate delegate);
}
