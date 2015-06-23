package org.shovelgame.engine.io;

import org.shovelgame.engine.session.command.Command;
import org.shovelgame.game.domain.data.Player;

public interface ClientDelegate {

	void disconnected();
	Player authenticate(Command command);
}
