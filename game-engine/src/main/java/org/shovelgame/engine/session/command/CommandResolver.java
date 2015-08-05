package org.shovelgame.engine.session.command;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientDelegate;

@Logger
public class CommandResolver {

	private ClientDelegate delegate;

	public CommandResolver(ClientDelegate delegate) {
		super();
		this.delegate = delegate;
	}

	public void process(Command command) throws CommandException {
		try {
			CommandProcessor processor = command.getName().instantiate();
			processor.process(command, delegate);
		} catch (Exception e) {
			throw new CommandException(e);
		}
	}

}
