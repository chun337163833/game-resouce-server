package org.shovelgame.engine.session.command.impl;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleTeam;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.BattleCommandProcessor;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.enumeration.MinionPosition;

@Logger
public class SyncMinionsCommand extends BattleCommandProcessor {

	@Override
	public void process(Command command, ClientDelegate delegate) throws CommandException {
		
		Battleground bg = battleDelegate.getBattleground();
		try {
			delegate.getClient().send(CommandName.SyncMinions.createCommand().asResponse());
			bg.getTeams().forEach((Communicator c, BattleTeam team) -> {
				team.getMinions().forEach((MinionPosition position, BattleMinion minion) -> {
					bg.getSession().sendAll(CommandName.EvtSyncMinion.createCommand(minion, team.getTeamId(), position.name()));
				});
			});
		} catch (ClientStreamException e) {
			log.error("", e);
		}		
	}

}
