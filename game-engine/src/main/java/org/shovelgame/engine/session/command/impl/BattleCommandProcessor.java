package org.shovelgame.engine.session.command.impl;

import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.session.command.CommandProcessor;
import org.shovelgame.engine.session.communication.Communicator;

public abstract class BattleCommandProcessor implements CommandProcessor {
	
	protected BattleDelegate battleDelegate;

	public void setBattleDelegate(BattleDelegate battleDelegate) {
		this.battleDelegate = battleDelegate;
	}
	
	public interface BattleDelegate {
		Communicator getCommunicator1();
		Communicator getCommunicator2();
		Battleground getBattleground();
	}
}
