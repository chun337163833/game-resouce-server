package org.shovelgame.engine.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.spring.oauth.TokenService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Logger
public class ServerConnection implements Runnable, InitializingBean {

	private TokenService tokenService;
	private TaskExecutor clientExecutor;
	private TaskExecutor serverExecutor;
	private int port;
	private ServerSocket socket;
	private List<ClientConnection> connectedClients = new ArrayList<ClientConnection>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.serverExecutor);
		Assert.notNull(this.clientExecutor);
	}
	
	public void startup() throws IOException {
		this.socket = new ServerSocket(this.port);
		this.serverExecutor.execute(this);
	}
	
	
	public void shutdown() throws IOException {
		this.socket.close();
	}

	public void run() {
		try {
			Socket socket;
			while ((socket = this.socket.accept()) != null) {
				ClientConnection client = new ClientConnection(socket, "ClientThread");
				client.setDelegate(new ClientDelegate() {
					@Override
					public void disconnected() {
						connectedClients.remove(client);
						client.interrupt();
					}

					@Override
					public void commandReceived(Command command) {

					}

					@Transactional(readOnly = true)
					@Override
					public Player authenticate(Command command) {
						try {
							String[] parameters = command.getParameters();
							String username = ServerConnection.this.tokenService.retrieveUserName(parameters[0]);
							if (username != null) {
								return Player.findPlayersByUserName(username).getSingleResult();
							}
						} catch (Exception e) {
							log.error("", e);
						}
						return null;
					}
				});
				this.connectedClients.add(client);
				client.start();
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	public void setClientExecutor(TaskExecutor clientExecutor) {
		this.clientExecutor = clientExecutor;
	}
	public void setServerExecutor(TaskExecutor serverExecutor) {
		this.serverExecutor = serverExecutor;
	}


	
}
