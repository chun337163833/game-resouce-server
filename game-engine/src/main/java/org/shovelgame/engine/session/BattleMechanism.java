package org.shovelgame.engine.session;

import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.communication.Communication;
import org.shovelgame.game.domain.data.Team;
import org.springframework.util.Assert;

public abstract class BattleMechanism {
	protected Communication team1;
	protected Communication team2;
	

	public BattleMechanism(Communication team1, Communication team2) {
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = team1;
		this.team2 = team2;
	}
	public void commandReceive(Command command) {
		
	}
	abstract void end(Team winner);
}
