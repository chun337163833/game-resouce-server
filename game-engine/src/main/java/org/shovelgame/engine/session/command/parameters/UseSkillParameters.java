package org.shovelgame.engine.session.command.parameters;

import org.shovelgame.game.domain.enumeration.MinionPosition;

public class UseSkillParameters {

	private MinionPosition target;
	private String teamId;
	private String skillId;

	public static UseSkillParameters read(String[] params) {
		UseSkillParameters p = new UseSkillParameters();
		p.teamId = params[0];
		p.target = MinionPosition.valueOf(params[1]);
		p.skillId = params[2];
		return p;
	}

	public MinionPosition getTarget() {
		return target;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getSkillId() {
		return skillId;
	}
}
