package org.shovelgame.engine.session.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;
import org.springframework.stereotype.Service;

@Service
@Logger
public class ServerConnection {
	
	private int port;
	private ServerSocket socket;
	
	public void startup() throws IOException {
		this.socket = new ServerSocket(this.port);
	}

	public void shutdown() throws IOException {
		this.socket.close();
	}
	
	public void listen() throws IOException {
		final Socket player = this.socket.accept();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					LineReader reader = new LineReader(player.getInputStream());
					String line = reader.readLine();
					Command command = Command.fromString(line);
				} catch (IOException e) {
					log.error("", e);
					try {
						player.close();
					} catch (IOException e1) {
						log.error("", e1);
					}
				}
			}
		}).start();
	}
	
}
