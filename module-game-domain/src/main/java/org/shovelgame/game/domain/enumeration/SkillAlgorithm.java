package org.shovelgame.game.domain.enumeration;

public enum SkillAlgorithm {

	Damage(TeamTarget.Enemy), 
	Heal(TeamTarget.Local, new MinionPosition[]{MinionPosition.Bot, MinionPosition.Top, MinionPosition.Mid, MinionPosition.Leader}), 
	Swap(TeamTarget.Local, false),
	Buff(TeamTarget.Local, new MinionPosition[]{MinionPosition.Bot, MinionPosition.Top, MinionPosition.Mid, MinionPosition.Leader}),
	Debuff(TeamTarget.Enemy, new MinionPosition[]{MinionPosition.Bot, MinionPosition.Top, MinionPosition.Mid, MinionPosition.Leader})
	;
	
	
	private MinionPosition[] targets = {MinionPosition.Bot, MinionPosition.Top, MinionPosition.Mid};
	private TeamTarget teamTarget;
	private boolean myself = true;
	
	
	
	private SkillAlgorithm(TeamTarget teamTarget, boolean myself) {
		this.myself = myself;
		this.teamTarget = teamTarget;
	}

	private SkillAlgorithm(TeamTarget teamTarget) {
		this.teamTarget = teamTarget;
	}

	private SkillAlgorithm(TeamTarget teamTarget, MinionPosition[] targets) {
		this.targets = targets;
		this.teamTarget = teamTarget;
	}

	private SkillAlgorithm(MinionPosition[] targets) {
		this.targets = targets;
	}
	public MinionPosition[] getTargets() {
		return targets;
	}
	public TeamTarget getTeamTarget() {
		return teamTarget;
	}
	public boolean isMyself() {
		return myself;
	}
}
