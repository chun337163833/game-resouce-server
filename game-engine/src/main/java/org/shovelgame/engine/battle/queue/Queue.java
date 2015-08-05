package org.shovelgame.engine.battle.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleTeam;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.enumeration.MinionPosition;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Queue {
	
	private QueuePosition[] positions;
	private Integer currentRound = 1;
	
	
	@JsonIgnore
	private int bufferSize = 20;
	
	@JsonIgnore
	private String startingTeam;
	
	@JsonIgnore
	private String secondTeam;

	
	@JsonIgnore
	private Battleground battleground;

	public Queue(Battleground battleground) {
		super();
		this.battleground = battleground;
	}

	public BattleMinion getCurrent() {
		QueuePosition position = this.positions[0];
		if (position == null) {
			return null;
		}
		return battleground.getMinionByQueuePosition(position);
	}

	public void initialize() {
		this.positions = new QueuePosition[bufferSize];
		this.resolveTeamPriority();
		this.update();
	}
	
	public String getFirstTeamInQueue() {
		QueuePosition q = this.positions[0];
		if(q == null) {
			return startingTeam;
		}
		return q.getTeamId();
	}
	
	private String switchTeam(String team) {
		return team.equals(startingTeam)? secondTeam: startingTeam;
	}
	
	public void update() {
		this.positions = new QueuePosition[bufferSize];
		this.reinitialize();
	}
	
	public void reinitialize() {
		int fightersPerRound = getFightersPerRound();
		int fightersPositioned = 0;
		String team = getFirstTeamInQueue();
		int round = this.currentRound;
		for (int i = 0; i < this.positions.length; i++) {
			QueuePosition pos = new QueuePosition();
			pos.setPosition(findPosition(team));
			pos.setTeamId(team);
			pos.setRound(round);
			this.positions[i] = pos;
			fightersPositioned++;
			team = switchTeam(team);
			if(fightersPerRound == fightersPositioned) {
				fightersPositioned = 0;
				round++;
				this.battleground.newRound();
			}
		}
	}
	
	
	
	private MinionPosition findPosition(String teamId) {
		List<QueuePosition> reverse = new ArrayList<>(Arrays.asList(this.positions));
		Collections.reverse(reverse);
		BattleTeam team = findTeamById(teamId);
		MinionPosition[] defaultOrder = team.getOrder();
		for(QueuePosition q: reverse) {
			if(q == null) {
				continue;
			}
			if(q.getTeamId().equals(teamId)) {
				MinionPosition lastPosition = q.getPosition();
				boolean next = false;
				for(MinionPosition p: defaultOrder) {
					if(next) {
						BattleMinion minion = team.getMinions().get(p);
						if(!minion.isDied()) {
							return p;
						} else {
							continue;
						}
					}
					if(p.equals(lastPosition)) {
						next = true;
					}
				}
				break;
			}
		}
		for(MinionPosition p: defaultOrder) {
			BattleMinion minion = team.getMinions().get(p);
			if(!minion.isDied()) {
				return p;
			}
		}
		return defaultOrder[0];
	}
	
	public BattleTeam next() {
		QueuePosition p = this.positions[1];
		this.positions = new QueuePosition[bufferSize];
		this.positions[0] = p;
		this.reinitialize();
		return getCurrentTeam();
	}
	
	public BattleTeam getCurrentTeam() {
		return findTeamById(this.positions[0].getTeamId());
	}
	
	private int getFightersPerRound() {
		int i = 0;
		for(Map.Entry<Communicator, BattleTeam> entry: this.battleground.getTeams().entrySet()) {
			BattleTeam team = entry.getValue();
			for(Map.Entry<MinionPosition, BattleMinion> entry2: team.getMinions().entrySet()) {
				BattleMinion minion = entry2.getValue();
				if(!minion.isDied()) {
					i++;
				}
			}
		}
		return i;
	}
	
	private BattleTeam findTeamById(String teamId) {
		for(Map.Entry<Communicator, BattleTeam> entry: this.battleground.getTeams().entrySet()) {
			BattleTeam team = entry.getValue();
			if(team.getTeamId().equals(teamId)) {
				return team;
			}
		}
		throw new IllegalArgumentException(String.format("no team found with id %s", teamId));
	}
	
	private void resolveTeamPriority() {
		this.startingTeam = Battleground.TEAM1;
		this.secondTeam = Battleground.TEAM2;
	}

	public Integer getCurrentRound() {
		return currentRound;
	}
}
