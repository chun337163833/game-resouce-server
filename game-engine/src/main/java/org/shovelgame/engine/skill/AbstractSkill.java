package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.FightingMinion;
import org.shovelgame.game.domain.model.MinionSkill;

public abstract class AbstractSkill implements ISkill {
	protected MinionSkill skill;

	public AbstractSkill(MinionSkill skill) {
		super();
		this.skill = skill;
	}

}
