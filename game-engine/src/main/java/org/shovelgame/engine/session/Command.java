package org.shovelgame.engine.session;

import org.shovelgame.game.domain.enumeration.Position;

public class Command {

	Long skill;
	Position source;
	Position target;

	public Long getSkill() {
		return skill;
	}

	public void setSkill(Long skill) {
		this.skill = skill;
	}

	public Position getSource() {
		return source;
	}

	public void setSource(Position source) {
		this.source = source;
	}

	public Position getTarget() {
		return target;
	}

	public void setTarget(Position target) {
		this.target = target;
	}

}
