package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.InputStream;

import org.shovelgame.engine.session.command.Command;

public class CommandInputStreamHelper {

	private LineReader reader;

	public CommandInputStreamHelper(InputStream input) {
		super();
		this.reader = new LineReader(input);
	}

	public Command read() throws IOException {
		return Command.fromString(reader.readLine().replace("\0", ""));
	}

}
