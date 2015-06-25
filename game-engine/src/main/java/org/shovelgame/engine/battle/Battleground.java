package org.shovelgame.engine.battle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.session.BattleSession;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.data.Team;
@Logger
public class Battleground {

	private Map<Communicator, FightingTeam> teams;
	private BattleSession session;
	
	public Battleground(Communicator comm1, Communicator comm2, BattleSession session) {
		super();
		this.teams = new HashMap<>();
		this.session = session;
		FightingTeam team1 = new FightingTeam(comm1.getTeam(), this);
		FightingTeam team2 = new FightingTeam(comm2.getTeam(), this);
		this.teams.put(comm1, team1);
		this.teams.put(comm2, team2);
		team1.build(() -> team2);
		team2.build(() -> team1);
	}

	public void update() {
		this.teams.forEach(new BiConsumer<Communicator, FightingTeam>() {
			@Override
			public void accept(Communicator t, FightingTeam u) {
				if(log.isDebugEnabled()) {
					log.debug(String.format("===Updating %s===", t));
				}
				u.updateTraits();
			}
		});
	}	
	public Map<Communicator, FightingTeam> getTeams() {
		return teams;
	}
	
	public FightingTeam getTeam(TeamType type, ClientConnection requestor) {
		for(Map.Entry<Communicator, FightingTeam> entry: this.teams.entrySet()) {
			boolean isMyTeam = entry.getKey().equals(requestor) && TeamType.My.equals(type);
			boolean isOpponentTeam = !entry.getKey().equals(requestor) && TeamType.Opponent.equals(type);
			if(isMyTeam || isOpponentTeam) {
				return entry.getValue();
			}
		}
		throw new IllegalStateException("Oops! Team for requestor not found!!");
	}
	public BattleSession getSession() {
		return session;
	}
	public Communicator getCommunicatorByTeam(FightingTeam team) {
		for(Map.Entry<Communicator, FightingTeam> entry: this.teams.entrySet()) {
			if(entry.getValue().equals(team)) {
				return entry.getKey();
			}
		}
		throw new IllegalStateException("Oops! No communicator by team found.");
	}

	public void gameEnd(FightingTeam winner) {
		Communicator c = getCommunicatorByTeam(winner);
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.My.name()));
		c = getCommunicatorByTeam(winner.getOpponentTeamDelegate().getTeam());
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.Opponent.name()));
		this.session.end(winner.getTeam());
	}
}
