package org.shovelgame.engine.skill;

import org.shovelgame.engine.session.command.BigData;

public class SkillResult implements BigData {

	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
