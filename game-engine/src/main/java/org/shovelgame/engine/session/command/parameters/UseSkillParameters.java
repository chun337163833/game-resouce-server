package org.shovelgame.engine.session.command.parameters;

import org.shovelgame.engine.battle.TeamType;
import org.shovelgame.game.domain.enumeration.MinionPosition;

public class UseSkillParameters {

	private MinionPosition target;
	private TeamType team;
	private String skillId;

	public static UseSkillParameters read(String[] params) {
		UseSkillParameters p = new UseSkillParameters();
		p.team = TeamType.valueOf(params[0]);
		p.target = MinionPosition.valueOf(params[1]);
		p.skillId = params[2];
		return p;
	}

	public MinionPosition getTarget() {
		return target;
	}

	public TeamType getTeam() {
		return team;
	}

	public String getSkillId() {
		return skillId;
	}
}
