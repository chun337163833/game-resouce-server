package org.shovelgame.engine.session;

import org.shovelgame.engine.session.communication.AICommunication;
import org.shovelgame.engine.session.communication.TcpCommunication;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.data.RewardClaimPK;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;

public class PveBattle extends BattleMechanism {

	private Mission mission;

	public PveBattle(Team playerTeam, Mission mission) {
		super(new TcpCommunication(playerTeam), new AICommunication(mission.getTeam()));
		this.mission = mission;
	}

	@Override
	public void end(Team winner) {
		if (winner != null) {
			Player player = winner.getOwner();
			for (MissionReward reward : mission.getMissionRewards()) {
				if (ChanceEvaluator.success(reward.getChance())) {
					RewardClaim rc = new RewardClaim();
					rc.setId(new RewardClaimPK(player.getId(), reward.getId()));
					rc.persist();
				}
			}
		}
	}

}
