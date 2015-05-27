package org.shovelgame.engine.session;

import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.data.RewardClaimPK;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;
import org.springframework.util.Assert;

public class PveSession implements BattleSession {

	private Team team1;
	private Team team2;
	private Mission mission;

	public PveSession(Team playerTeam, Mission mission) {
		Assert.notNull(team1, "team1 argument is mandatory");
		Assert.notNull(team2, "team2 argument is mandatory");
		this.team1 = playerTeam;
		this.team2 = mission.getTeam();
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
