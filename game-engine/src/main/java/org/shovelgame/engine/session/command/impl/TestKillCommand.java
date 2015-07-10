package org.shovelgame.engine.session.command.impl;

import java.math.BigDecimal;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleTeam;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.battle.Stat;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.BattleCommandProcessor;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.environment.Environment;
import org.shovelgame.environment.EnvironmentType;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.MinionPosition;

@Logger
@Environment(value={EnvironmentType.TEST, EnvironmentType.DEVELOPMENT})
public class TestKillCommand extends BattleCommandProcessor {
	@Override
	public void process(Command command, ClientDelegate delegate)
			throws CommandException {
		Battleground bg = battleDelegate.getBattleground();
		try {
			MinionPosition[] positions = MinionPosition.valueOf(command.getParameters());
			BattleTeam team = bg.getTeam(Battleground.TEAM1);
			for(MinionPosition p: positions) {
				BattleMinion minion = team.getMinions().get(p);
				Stat stat = minion.getStat(AttributeManagedType.Health);
				stat.changeValue(new BigDecimal(0));
			}
			delegate.getClient().send(CommandName.TestKill.createCommand().asResponse());
		} catch (ClientStreamException e) {
			log.error("", e);
		}
	}
}
