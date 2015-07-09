package org.shovelgame.engine.skill.impl;

import java.util.Arrays;
import java.util.List;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.battle.BattleTeam;
import org.shovelgame.engine.skill.AbstractSkill;
import org.shovelgame.engine.skill.Skill;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

@Skill(SkillAlgorithm.Swap)
public class Swap extends AbstractSkill {

	public Swap(BattleSkill skill) {
		super(skill);
	}

	@Override
	public SkillResult process(BattleMinion source, BattleMinion target)
			throws SkillUsageException {
		BattleTeam team = source.getTeam();
		SkillResult res = new SkillResult();
		res.setSwap(true);
		MinionPosition sourcePosition = source.getPosition();
		MinionPosition targetPosition = target.getPosition();
		team.getMinions().put(sourcePosition, target);
		team.getMinions().put(targetPosition, source);
		List<MinionPosition> positions = Arrays.asList(team.getOrder());
		int sourceIndex = positions.indexOf(sourcePosition);
		int targetIndex = positions.indexOf(targetPosition);
		positions.set(sourceIndex, targetPosition);
		positions.set(targetIndex, sourcePosition);
		return res;
	}


}
