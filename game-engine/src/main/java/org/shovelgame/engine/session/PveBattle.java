package org.shovelgame.engine.session;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandDelegate;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.communication.AICommunicator;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.engine.session.communication.TcpCommunicator;
import org.shovelgame.game.domain.ChanceEvaluator;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.data.RewardClaimPK;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;

@Logger
public class PveBattle extends BattleMechanism implements CommandDelegate {

	private Mission mission;

	public PveBattle(ClientConnection client, Mission mission) {
		super(new TcpCommunicator(client), new AICommunicator(
				mission.getTeam()));
		this.mission = mission;
	}

	@Override
	public void begin() {
		Communicator c = getPlayerCommunicator();
		try {
			c.send(CommandName.Mission.createCommand("Begin"));
		} catch (ClientStreamException e) {
			log.error("", e);
		}
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
	
	private Communicator getPlayerCommunicator() {
		return this.team1;
	}
	
	@Override
	public void received(Command command) {
		System.out.println(command);
	}
	
}
