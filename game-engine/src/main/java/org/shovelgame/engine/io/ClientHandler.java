package org.shovelgame.engine.io;

import java.util.List;

import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Player;

public interface ClientHandler {

	void disconnected();
	Player authenticate(Command command);
	List<ClientConnection> getQueue();
}
