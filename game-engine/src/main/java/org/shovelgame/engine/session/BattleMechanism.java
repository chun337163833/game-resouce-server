package org.shovelgame.engine.session;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandDelegate;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.engine.session.command.impl.BattleCommandProcessor;
import org.shovelgame.engine.session.command.impl.BattleCommandProcessor.BattleDelegate;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

@Logger
public abstract class BattleMechanism implements BattleDelegate, CommandDelegate {
	protected Communicator team1;
	protected Communicator team2;
	private Battleground battleground;
	
	public BattleMechanism(Communicator team1, Communicator team2) {
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = team1;
		this.team2 = team2;
		this.battleground = new Battleground(team1.getTeam(), team2.getTeam());
	}

	public abstract void begin();

	abstract void end(Team winner);

	@Override
	public Communicator getCommunicator1() {
		return this.team1;
	}

	@Override
	public Communicator getCommunicator2() {
		return this.team2;
	}

	@Override
	public Battleground getBattleground() {
		return this.battleground;
	}
	
	@Override
	public void received(Command command, final ClientConnection from) throws CommandException {
		try {
			CommandProcessor processor = command.getName().instantiate();
			if (processor instanceof BattleCommandProcessor) {
				BattleCommandProcessor battleProcessor = (BattleCommandProcessor) processor;
				battleProcessor.setBattleDelegate(this);
				battleProcessor.process(command, () -> from);
			}
		} catch (Exception e) {
			throw new CommandException(e);
		}
	}
}
