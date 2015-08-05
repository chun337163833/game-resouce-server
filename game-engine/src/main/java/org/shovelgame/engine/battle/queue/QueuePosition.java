package org.shovelgame.engine.battle.queue;

import org.shovelgame.game.domain.enumeration.MinionPosition;

public class QueuePosition {

	private MinionPosition position;
	private String teamId;
	private Integer round;

	public MinionPosition getPosition() {
		return position;
	}

	public void setPosition(MinionPosition position) {
		this.position = position;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	@Override
	public String toString() {
		return String.format("QueuePosition[%s, %s, %d]\n", this.teamId, this.position.name(), this.round);
	}
	
}
