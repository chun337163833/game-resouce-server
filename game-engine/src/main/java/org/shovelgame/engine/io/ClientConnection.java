package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandDelegate;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandStatus;
import org.shovelgame.engine.session.command.ErrorMessageCommand;
import org.shovelgame.game.domain.data.Player;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Logger
public class ClientConnection extends Thread implements Runnable {
	private Socket socket;
	private ClientDelegate clientDelegate;
	private CommandDelegate commandDelegate;
	private Player player;

	public ClientConnection(Socket socket, String name) {
		super(name);
		this.socket = socket;
	}

	public void setClientDelegate(ClientDelegate clientDelegate) {
		this.clientDelegate = clientDelegate;
	}

	public synchronized void setCommandDelegate(CommandDelegate commandDelegate) {
		this.commandDelegate = commandDelegate;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void run() {
		try {
			Command command = null;
			while ((command = listen()) != null) {
				if (this.player == null) {
					if (CommandName.Authentication.equals(command.getName())) {
						this.player = this.clientDelegate.authenticate(command);
						if (this.player == null) {
							this.sendAuthenticationFailed();
						} else {
							this.sendAuthenticationSuccess();
						}
					} else {
						this.sendAuthenticationRequired();
					}
				} else {
					this.commandDelegate.received(command);
				}
			}
			if (!socket.isClosed())
				socket.close();
			this.clientDelegate.disconnected();
		} catch (Exception e) {
			log.error("", e);
			this.clientDelegate.disconnected();
			if (!socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e1) {
					log.error("", e1);
				}
			}
		}
	}

	private void sendAuthenticationRequired() throws ClientStreamException {
		send(CommandName.Authentication
				.createCommand("Authentication Required").setStatus(
						CommandStatus.AccessDenied));
	}

	private void sendAuthenticationFailed() throws ClientStreamException {
		send(CommandName.Authentication.createCommand("Authentication failed.")
				.setStatus(CommandStatus.Error));
	}

	private void sendAuthenticationSuccess() throws ClientStreamException {
		send(CommandName.Authentication
				.createCommand("Successfully authenticated."));
	}

	public void sendError(Command unproceseedCommand, String message)
			throws ClientStreamException {
		send(ErrorMessageCommand.fromOriginalCommand(unproceseedCommand,
				message));
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
		if (line == null) {
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
