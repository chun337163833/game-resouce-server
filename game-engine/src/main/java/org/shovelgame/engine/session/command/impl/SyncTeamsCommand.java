package org.shovelgame.engine.session.command.impl;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandProcessor;

@Logger
public class SyncTeamsCommand extends BattleCommandProcessor implements CommandProcessor {

	@Override
	public void process(Command command, ClientDelegate delegate) throws CommandException {
		
		Battleground bg = battleDelegate.getBattleground();
		Command syncComand = CommandName.SyncTeams.createCommand(bg);
		try {
			delegate.getClient().send(syncComand);
		} catch (ClientStreamException e) {
			log.error("", e);
		}
		
	}

}
