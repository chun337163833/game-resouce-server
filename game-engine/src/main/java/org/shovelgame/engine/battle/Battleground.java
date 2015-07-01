package org.shovelgame.engine.battle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

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
import org.shovelgame.game.domain.model.MinionSkill;
import org.springframework.beans.factory.annotation.Configurable;
@Logger
@Configurable
public class Battleground {

	private Map<Communicator, FightingTeam> teams;
	private BattleSession session;
	private Queue queue;
	
	@Resource(name="skillExecutor")
	private SkillExecutor executor;
	
	public Battleground(Communicator comm1, Communicator comm2, BattleSession session) {
		super();
		this.teams = new HashMap<>();
		this.session = session;
		FightingTeam team1 = new FightingTeam(comm1.getTeam(), this);
		FightingTeam team2 = new FightingTeam(comm2.getTeam(), this);
		this.teams.put(comm1, team1);
		this.teams.put(comm2, team2);
		//first initialize all traits across all minions of all teams
		team1.initializeTraits(() -> team2);
		team2.initializeTraits(() -> team1);
		//when all traits are initialized, just initilize all stats
		team1.initializeStats();
		team2.initializeStats();
		this.queue = new Queue(team1, team2);
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

	
	public SkillResult useSkill(UseSkillParameters params, ClientConnection requestor) throws SkillUsageException {
		FightingMinion source = this.queue.getCurrent();
		FightingMinion target = getTeam(params.getTeam(), requestor).getMinions().get(params.getTarget());
		MinionSkill skill = source.getMinion().getMinionModel().findSkill(params.getSkillId());
		if(skill == null) {
			throw new SkillUsageException(String.format("Skill %s not found", params.getSkillId()));
		}
		return this.executor.execute(skill, source, target);
	}

	public void gameEnd(FightingTeam winner) {
		Communicator c = getCommunicatorByTeam(winner);
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.My.name()));
		c = getCommunicatorByTeam(winner.getOpponentTeamDelegate().getTeam());
		c.send(CommandName.EvtGameEnd.createCommand(TeamType.Opponent.name()));
		this.session.end(winner.getTeam());
	}
}
