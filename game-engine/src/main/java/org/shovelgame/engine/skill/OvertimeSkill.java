package org.shovelgame.engine.skill;

import org.shovelgame.engine.battle.BattleMinion;

public interface OvertimeSkill extends CountdownSkill {
	public SkillResult tick(BattleMinion target);
}
