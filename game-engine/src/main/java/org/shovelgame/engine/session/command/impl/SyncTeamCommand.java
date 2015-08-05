package org.shovelgame.engine.session.command.impl;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.BattleCommandProcessor;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;

@Logger
public class SyncTeamCommand extends BattleCommandProcessor {

	@Override
	public void process(Command command, ClientDelegate delegate) throws CommandException {
		
		Battleground bg = battleDelegate.getBattleground();
		String teamId = String.valueOf(command.getParameters()[0]);
		Command syncComand = CommandName.SyncTeam.createCommand(bg.getTeam(teamId));
		try {
			delegate.getClient().send(syncComand.asResponse());
		} catch (ClientStreamException e) {
			log.error("", e);
		}
	}

}
