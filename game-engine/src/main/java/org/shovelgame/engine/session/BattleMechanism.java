package org.shovelgame.engine.session;

import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public abstract class BattleMechanism {
	protected Communicator team1;
	protected Communicator team2;
	

	public BattleMechanism(Communicator team1, Communicator team2) {
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public abstract void begin();
	
	public void commandReceive(Command command) {
		
	}
	abstract void end(Team winner);
}
