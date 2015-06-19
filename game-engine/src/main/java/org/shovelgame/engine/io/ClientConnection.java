package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandStatus;
import org.shovelgame.game.domain.data.Player;

@Logger
public class ClientConnection extends Thread implements Runnable {
	private Socket socket;
	private ClientDelegate delegate;
	private Player player;

	public ClientConnection(Socket socket, String name) {
		super(name);
		this.socket = socket;
	}

	public void setDelegate(ClientDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void run() {
		try {
			Command command = null;
			while ((command = listen()) != null) {
				if (this.player == null) {
					if (CommandName.Authentication.equals(command.getName())) {
						this.player = this.delegate.authenticate(command);
						if(this.player == null) {
							this.sendAuthenticationFailed();
						} else {
							this.sendAuthenticationSuccess();
						}
					} else {
						this.sendAuthenticationRequired();
					}
				} else {
					this.delegate.commandReceived(command);
				}
			}
			if(!socket.isClosed())
				socket.close();
			delegate.disconnected();
		} catch (Exception e) {
			log.error("", e);
			delegate.disconnected();
			if(!socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e1) {
					log.error("", e1);
				}
			}
		}
	}

	private void sendAuthenticationRequired() throws ClientStreamException {
		send(CommandName.Authentication.createCommand("Authentication Required").setStatus(CommandStatus.AccessDenied));
	}

	private void sendAuthenticationFailed() throws ClientStreamException {
		send(CommandName.Authentication.createCommand("Authentication failed.").setStatus(CommandStatus.Error));
	}
	
	private void sendAuthenticationSuccess() throws ClientStreamException {
		send(CommandName.Authentication.createCommand("Successfully authenticated."));
	}
	
	public void send(Command command) throws ClientStreamException {
		try {
			command.validate();
			String str = command.toJson();
			OutputStream os = this.socket.getOutputStream();
			os.write(str.getBytes());
			os.write("\n".getBytes());
		} catch (Exception e) {
			throw new ClientStreamException(e);
		}

	}

	private Command listen() throws Exception {
		LineReader reader = new LineReader(socket.getInputStream());
		String line = reader.readLine();
		if(line == null) {
			return null;
		}
		Command command = Command.fromString(line);
		command.validate();
		return command;
	}

	public Player getPlayer() {
		return player;
	}
}
