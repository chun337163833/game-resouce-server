package org.shovelgame.engine.session.command.impl;

import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.session.PveBattle;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.game.domain.model.Mission;

public class MissionCommand implements CommandProcessor {

	@Override
	public void process(Command command, ClientDelegate delegate) {
		Long id = Long.valueOf(command.getParameters()[0]);
		Mission mission = Mission.findMission(id);
		ClientConnection client = delegate.getClient();
		PveBattle battle = new PveBattle(client, mission);
		client.setCommandDelegate(battle);
		battle.begin();
	}
	
}
