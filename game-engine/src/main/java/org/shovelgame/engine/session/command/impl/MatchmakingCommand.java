package org.shovelgame.engine.session.command.impl;

import java.util.List;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.io.PlayerConnection;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.game.domain.data.Player;


public class MatchmakingCommand implements CommandProcessor {

	
	@Override
	public void process(Command command, ClientDelegate delegate) {
		PlayerConnection client = (PlayerConnection) delegate.getClient();
		List<PlayerConnection> queue = client.getHandler().getQueue();
		//find match only if queue has more then 1 connected client
		if(queue.size() > 1) {
			PlayerConnection opponent = findOptimalOpponent(queue, client.getPlayer());
			startMatch(client, opponent);
		}
		//or just stay in queue and wait for some players
	}

	private void startMatch(ClientConnection p1, ClientConnection p2) {
		
	}
	
	private PlayerConnection findOptimalOpponent(List<PlayerConnection> queue, Player player) {
		return queue.get(0);
	}
	
}
