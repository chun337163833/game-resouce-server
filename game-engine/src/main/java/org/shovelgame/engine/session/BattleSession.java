package org.shovelgame.engine.session;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.session.command.BattleCommandProcessor;
import org.shovelgame.engine.session.command.BattleCommandProcessor.BattleDelegate;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandDelegate;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

@Logger
public abstract class BattleSession implements BattleDelegate, CommandDelegate {
	protected Communicator team1;
	protected Communicator team2;
	private Battleground battleground;
	private boolean gameEnd;

	public BattleSession(Communicator team1, Communicator team2) {
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = team1;
		this.team2 = team2;
		this.battleground = new Battleground(team1, team2, this);
	}

	public abstract void begin();

	public abstract void end(Team winner);

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

	public void sendAll(Command command) {
		team1.send(command);
		team2.send(command);
	}

	@Override
	public void received(Command command, ClientDelegate from) throws CommandException {
		try {
			CommandProcessor processor = command.getName().instantiate();
			if (processor instanceof BattleCommandProcessor) {
				BattleCommandProcessor battleProcessor = (BattleCommandProcessor) processor;
				battleProcessor.setBattleDelegate(this);
				battleProcessor.process(command, from);
			}
		} catch (Exception e) {
			throw new CommandException(e);
		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

}
