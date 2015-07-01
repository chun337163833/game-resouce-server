package org.shovelgame.engine.battle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.session.BattleSession;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.parameters.UseSkillParameters;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.engine.skill.SkillExecutor;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.springframework.beans.factory.annotation.Configurable;
@Logger
@Configurable
public class Battleground {

	private Map<Communicator, BattleTeam> teams;
	private BattleSession session;
	private Queue queue;
	
	@Resource(name="skillExecutor")
	private SkillExecutor executor;
	
	public Battleground(Communicator comm1, Communicator comm2, BattleSession session) {
		super();
		this.teams = new HashMap<>();
		this.session = session;
		BattleTeam team1 = new BattleTeam(comm1.getTeam(), this);
		BattleTeam team2 = new BattleTeam(comm2.getTeam(), this);
		this.teams.put(comm1, team1);
		this.teams.put(comm2, team2);
		//first initialize all traits across all minions of all teams
		team1.setOpponentDelegate(() -> team2);
		team2.setOpponentDelegate(() -> team1);
		//when all traits are initialized, just initilize all stats
		this.update();
		this.queue = new Queue(team1, team2);
	}

	public void update() {
		this.teams.forEach((Communicator t, BattleTeam u) -> u.updateTraits());
		this.teams.forEach((Communicator t, BattleTeam u) -> {
			u.getMinions().forEach((MinionPosition o, BattleMinion m) -> {for(Stat s: m.getStats()) {
				s.recalculate();
			}});
			
		});
	}	
	public Map<Communicator, BattleTeam> getTeams() {
		return teams;
	}
	
	public BattleTeam getTeam(TeamType type, ClientConnection requestor) {
		for(Map.Entry<Communicator, BattleTeam> entry: this.teams.entrySet()) {
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
	public Communicator getCommunicatorByTeam(BattleTeam team) {
		for(Map.Entry<Communicator, BattleTeam> entry: this.teams.entrySet()) {
			if(entry.getValue().equals(team)) {
				return entry.getKey();
			}
		}
		throw new IllegalStateException("Oops! No communicator by team found.");
	}

	
	public SkillResult useSkill(UseSkillParameters params, ClientConnection requestor) throws SkillUsageException {
		BattleMinion source = this.queue.getCurrent();
		BattleMinion target = getTeam(params.getTeam(), requestor).getMinions().get(params.getTarget());
		BattleSkill skill = source.findSkill(params.getSkillId());
		if(skill == null) {
			throw new SkillUsageException(String.format("Skill %s not found", params.getSkillId()));
		}
		return this.executor.execute(skill, source, target);
	}

	public void gameEnd(BattleTeam winner) {
		Communicator c = getCommunicatorByTeam(winner);
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.My.name()));
		c = getCommunicatorByTeam(winner.getOpponentTeamDelegate().getTeam());
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.Opponent.name()));
		this.session.end(winner.getTeam());
	}
	

	
}
