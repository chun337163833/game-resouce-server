package org.shovelgame.engine.io;

import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Player;

public interface ClientDelegate {

	void disconnected();
	void commandReceived(Command command);
	Player authenticate(Command command);
}
