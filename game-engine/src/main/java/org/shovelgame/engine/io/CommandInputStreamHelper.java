package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.shovelgame.engine.session.command.Command;

public class CommandInputStreamHelper {

	private LineReader reader;

	public CommandInputStreamHelper(InputStream input) {
		super();
		this.reader = new LineReader(input);
	}

	public Command read() throws IOException {
		String line = reader.readLine();
		line = line.replaceAll("[=\0 ]", "");
		String decoded = new String(Base64.getDecoder().decode(line.getBytes()));
		return Command.fromString(decoded);
	}

}
