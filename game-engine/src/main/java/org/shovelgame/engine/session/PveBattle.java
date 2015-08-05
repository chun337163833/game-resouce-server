package org.shovelgame.engine.session;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.ai.AI;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.communication.AICommunicator;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.engine.session.communication.TcpCommunicator;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;

@Logger
public class PveBattle extends BattleSession {

	private Mission mission;
	
	public PveBattle(TcpCommunicator tcpcommunicator, AICommunicator aicommunicator) {
		super(tcpcommunicator, aicommunicator);
		this.mission = aicommunicator.getMission();
		aicommunicator.setAi(new AI(getBattleground()));
	}

	@Override
	public void begin() {
		Communicator c = getPlayerCommunicator();
		c.send(CommandName.Mission.createCommand().asResponse());
		getBattleground().start();
	}
	
	@Override
	public void end(Team winner) {
		Player player = winner.getOwner();
		//if players win get rewards and store them to database
		if(player != null)
		for (MissionReward reward : mission.getMissionRewards()) {
			if (ChanceEvaluator.success(reward.getChance())) {
				RewardClaim rc = new RewardClaim();
				rc.setPlayer(player);
				rc.setReward(reward);
				rc.persist();
			}
		}
	}
	
	private Communicator getPlayerCommunicator() {
		return getCommunicator1();
	}
	
}
