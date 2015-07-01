package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.BattleSkill;

public abstract class AbstractSkill implements ISkill {
	protected BattleSkill skill;
	public AbstractSkill(BattleSkill skill) {
		super();
		this.skill = skill;
	}

}
