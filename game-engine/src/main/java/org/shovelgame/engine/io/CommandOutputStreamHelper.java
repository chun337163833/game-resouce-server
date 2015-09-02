package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;

@Logger
public class CommandOutputStreamHelper {

	private OutputStream output;

	public CommandOutputStreamHelper(OutputStream output) {
		super();
		this.output = output;
	}

	public void send(Command command) throws IOException {
		sendRaw(command.toJson());
		log.info(String.format("Command(%s) > %s", command.getName().name(), command.getStatus().name()));
//		try {
//			Thread.sleep(50);
//		} catch (InterruptedException e) {
//			log.debug("", e);
//		}
	}
	
	public void sendRaw(String s ) throws IOException {
		byte[] data = Base64.getEncoder().encode(s.getBytes());
		
		output.write(data);
		output.write('\n');
	}
	

}
