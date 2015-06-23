package org.shovelgame.engine.session.command;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.io.ServerDelegate;

@Logger
public class CommandResolver {

	private ClientConnection client;
	private ServerDelegate delegate;

	public CommandResolver(ClientConnection client, ServerDelegate delegate) {
		super();
		this.client = client;
		this.delegate = delegate;
	}

	public void process(Command command) {
		try {
			CommandProcessor processor = command.getName().instantiate();
			processor.process(command, delegate);
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("", e);
			try {
				client.sendError(command, e.getMessage());
			} catch (ClientStreamException e1) {
				log.error("", e);
			}
		}
	}
	
	

}
