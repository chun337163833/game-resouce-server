package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.OutputStream;

import org.shovelgame.engine.session.command.Command;

public class CommandOutputStreamHelper {

	private OutputStream output;

	public CommandOutputStreamHelper(OutputStream output) {
		super();
		this.output = output;
	}

	public void send(Command command) throws IOException {
		output.write(command.toJson().getBytes());
		output.write("\n".getBytes());
	}


}
