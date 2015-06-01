package org.shovelgame.engine.session;

import org.shovelgame.engine.session.communication.Communication;
import org.shovelgame.game.domain.data.Team;

public class AutoBattle extends BattleMechanism {

	public AutoBattle(Communication team1, Communication team2) {
		super(team1, team2);
		
	}

	@Override
	public void end(Team winner) {
		
	}



}
