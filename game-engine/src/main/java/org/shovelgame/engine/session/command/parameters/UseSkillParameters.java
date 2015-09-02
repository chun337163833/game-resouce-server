package org.shovelgame.engine.session.command.parameters;

import org.shovelgame.game.domain.enumeration.MinionPosition;

public class UseSkillParameters {

	private MinionPosition target;
	private MinionPosition source;

	private String sourceTeam;
	private String targetTeam;

	private String skillId;

	public static UseSkillParameters read(String[] params) {
		UseSkillParameters p = new UseSkillParameters();
		p.targetTeam = params[0];
		p.skillId = params[1];
		p.target = MinionPosition.valueOf(params[2]);
		if (params.length > 3)
			p.source = MinionPosition.valueOf(params[3]);
		return p;
	}

	public MinionPosition getTarget() {
		return target;
	}

	public String getSkillId() {
		return skillId;
	}

	public MinionPosition getSource() {
		return source;
	}

	public void setSource(MinionPosition source) {
		this.source = source;
	}

	public void setTarget(MinionPosition target) {
		this.target = target;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getSourceTeam() {
		return sourceTeam;
	}

	public void setSourceTeam(String sourceTeam) {
		this.sourceTeam = sourceTeam;
	}

	public String getTargetTeam() {
		return targetTeam;
	}

	public void setTargetTeam(String targetTeam) {
		this.targetTeam = targetTeam;
	}

}
