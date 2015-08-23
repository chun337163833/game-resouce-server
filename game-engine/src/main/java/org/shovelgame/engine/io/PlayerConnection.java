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
public class PlayerConnection implements Runnable, ClientConnection {
	private Socket socket;
	private PlayerHandler handler;
	private CommandDelegate commandDelegate;
	private Player player;
	public PlayerConnection(Socket socket) {
		this.socket = socket;
	}

	public void setClientDelegate(PlayerHandler clientDelegate) {
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
				} else if(command.getName().equals(CommandName.KeepAlive)){
					//TODO keepalive
				} else {
					try {
						this.commandDelegate.received(command, () -> this);
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
				.createCommand("Successfully authenticated.").asResponse());
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
		try {
			Command command = helper.read();
			command.validate();
			return command;
		} catch (Exception e) {
			log.error("Error");
			return null;
		}
		
	}

	public Player getPlayer() {
		return player;
	}
	public PlayerHandler getHandler() {
		return handler;
	}
}
