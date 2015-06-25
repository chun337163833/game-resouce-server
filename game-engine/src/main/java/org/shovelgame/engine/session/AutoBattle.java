package org.shovelgame.engine.session;

import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.environment.Environment;
import org.shovelgame.environment.EnvironmentType;
import org.shovelgame.game.domain.data.Team;

@Environment(value = { EnvironmentType.TEST, EnvironmentType.DEVELOPMENT })
public class AutoBattle extends BattleSession {

	public AutoBattle(Communicator team1, Communicator team2) {
		super(team1, team2);

	}

	@Override
	public void end(Team winner) {

	}

	
	@Override
	public void begin() {
		// TODO Auto-generated method stub

	}

}
