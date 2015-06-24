package org.shovelgame.engine.io;

import java.io.IOException;
import java.net.Socket;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandDelegate;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandStatus;
import org.shovelgame.engine.session.command.ErrorMessageCommand;
import org.shovelgame.game.domain.data.Player;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Logger
public class ClientConnection extends Thread implements Runnable {
	private Socket socket;
	private ClientHandler handler;
	private CommandDelegate commandDelegate;
	private Player player;

	public ClientConnection(Socket socket, String name) {
		super(name);
		this.socket = socket;
	}

	public void setClientDelegate(ClientHandler clientDelegate) {
		this.handler = clientDelegate;
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
						this.player = this.handler.authenticate(command);
						if (this.player == null) {
							this.sendAuthenticationFailed();
						} else {
							this.sendAuthenticationSuccess();
						}
					} else {
						this.sendAuthenticationRequired();
					}
				} else {
					try {
						this.commandDelegate.received(command, this);
					} catch (CommandException e) {
						log.error("", e);
						this.sendError(command, e.getMessage());
					}
					
				}
			}
			if (!socket.isClosed())
				socket.close();
			this.handler.disconnected();
		} catch (Exception e) {
			log.error("", e);
			this.handler.disconnected();
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
			CommandOutputStreamHelper os = new CommandOutputStreamHelper(this.socket.getOutputStream());
			command.validate();
			os.send(command);
		} catch (Exception e) {
			throw new ClientStreamException(e);
		}

	}

	private Command listen() throws Exception {
		CommandInputStreamHelper helper = new CommandInputStreamHelper(socket.getInputStream());
		Command command = helper.read();
		command.validate();
		return command;
	}

	public Player getPlayer() {
		return player;
	}
	public ClientHandler getHandler() {
		return handler;
	}
}
