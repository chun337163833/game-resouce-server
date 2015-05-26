package org.shovelgame.engine.session;

import org.shovelgame.game.domain.data.Team;

public interface BattleSession {

	void end(Team winner);
	
}
