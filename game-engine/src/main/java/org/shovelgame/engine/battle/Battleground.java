package org.shovelgame.engine.battle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.queue.Queue;
import org.shovelgame.engine.battle.queue.QueuePosition;
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
	public static final String TEAM1 = "team1";
	public static final String TEAM2 = "team2";
	
	private Map<Communicator, BattleTeam> teams;
	private BattleSession session;
	private Queue queue;
	
	@Resource(name="skillExecutor")
	private SkillExecutor executor;
	
	public Battleground(Communicator comm1, Communicator comm2, BattleSession session) {
		super();
		this.teams = new HashMap<>();
		this.session = session;
		BattleTeam team1 = new BattleTeam(comm1.getTeam(), this, TEAM1);
		BattleTeam team2 = new BattleTeam(comm2.getTeam(), this, TEAM2);
		this.teams.put(comm1, team1);
		this.teams.put(comm2, team2);
		//first initialize all traits across all minions of all teams
		team1.setOpponentDelegate(() -> team2);
		team2.setOpponentDelegate(() -> team1);
		//when all traits are initialized, just initilize all stats
		this.update();
		this.queue = new Queue(this);
		this.queue.initialize();
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
	
	public BattleTeam getTeam(String teamId, ClientConnection requestor) {
		for(Map.Entry<Communicator, BattleTeam> entry: this.teams.entrySet()) {
			BattleTeam team = entry.getValue();
			if(team.getTeamId().equals(teamId)) {
				return team;
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
		BattleMinion target = getTeam(params.getTeamId(), requestor).getMinions().get(params.getTarget());
		BattleSkill skill = source.findSkill(params.getSkillId());
		if(skill == null) {
			throw new SkillUsageException(String.format("Skill %s not found", params.getSkillId()));
		}
		if(!skill.canUse(params)) {
			throw new SkillUsageException(String.format("Skill %s cannot be used to %s -> %s", params.getSkillId(), params.getTeamId(), params.getTarget().name()));	
		}
		SkillResult result = this.executor.execute(skill, source, target);
		this.update();
		return result;
	}

	public BattleMinion getMinionByQueuePosition(QueuePosition position) {
		for(Map.Entry<Communicator, BattleTeam> entry: this.teams.entrySet()) {
			BattleTeam team = entry.getValue();
			if(team.getTeamId().equals(position.getTeamId())) {
				return team.getMinions().get(position.getPosition());
			}
		}
		throw new IllegalStateException(String.format("Not minion found for queue position %s", position.toString()));
	}
	
	public void gameEnd(BattleTeam winner) {
		this.teams.forEach((Communicator c, BattleTeam team) -> c.send(CommandName.EvtGameEnd.createCommand(winner.getTeamId())));
		this.session.end(winner.getTeam());
	}
	
	public void start() {
		//first send teamid to client
		this.teams.forEach((Communicator c, BattleTeam t) -> c.send(CommandName.EvtTeamIdAssociation.createCommand(t.getTeamId())));
		BattleTeam nextTeam = queue.getCurrentTeam();
		nextTeam.getCommunicator().send(CommandName.EvtStartTurn.createCommand());
	}
	
	public void nextTurn() {
		BattleTeam nextTeam = queue.next();
		nextTeam.getCommunicator().send(CommandName.EvtStartTurn.createCommand());
	}
	public Queue getQueue() {
		return queue;
	}
}
