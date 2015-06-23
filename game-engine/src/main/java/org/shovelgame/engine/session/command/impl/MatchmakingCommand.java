package org.shovelgame.engine.session.command.impl;

import java.util.List;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ServerDelegate;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.game.domain.data.Player;


public class MatchmakingCommand implements CommandProcessor {

	
	@Override
	public void process(Command command, ServerDelegate delegate) {
		ClientConnection client = delegate.getClient();
		List<ClientConnection> queue = delegate.getQueue();
		//find match only if queue has more then 1 connected client
		if(queue.size() > 1) {
			ClientConnection opponent = findOptimalOpponent(queue, client.getPlayer());
			startMatch(client, opponent);
		}
		//or just stay in queue and wait for some players
	}

	private void startMatch(ClientConnection p1, ClientConnection p2) {
		
	}
	
	private ClientConnection findOptimalOpponent(List<ClientConnection> queue, Player player) {
		return queue.get(0);
	}
	
}
