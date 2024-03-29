package org.shovelgame.engine.session.command;

import org.shovelgame.engine.session.command.impl.MatchmakingCommand;
import org.shovelgame.engine.session.command.impl.MissionCommand;
import org.shovelgame.engine.session.command.impl.SyncMinionsCommand;
import org.shovelgame.engine.session.command.impl.SyncTeamCommand;
import org.shovelgame.engine.session.command.impl.TestDamageCommand;
import org.shovelgame.engine.session.command.impl.TestKillCommand;
import org.shovelgame.engine.session.command.impl.TurnEndCommand;
import org.shovelgame.engine.session.command.impl.UseSkillCommand;

public enum CommandName {
	Authentication,
	Matchmaking(MatchmakingCommand.class),
	Mission(MissionCommand.class),
	KeepAlive,
	/**
	 * 0 = target team id
	   1 = skill id
	   2 = target minion position
	   3 = source minion position -> optional, depends on skill
	 */
	UseSkill(UseSkillCommand.class),
	SyncTeam(SyncTeamCommand.class),
	TestKill(TestKillCommand.class),
	TestDamage(TestDamageCommand.class),
	TurnEnd(TurnEndCommand.class),
	SyncMinions(SyncMinionsCommand.class),
	
	EvtGameEnd(true),
	EvtSkillUsed(true),
	EvtStartTurn(true),
	EvtEnemyTurn(true),
	EvtTeamIdAssociation(true),
	EvtCommandError(true),
	EvtSyncMinion(true)
	;
	
	private Class<? extends CommandProcessor> processor;
	private boolean event;
	private CommandName() {
		// TODO Auto-generated constructor stub
	}
	
	
	private CommandName(boolean event) {
		this.event = event;
	}


	private CommandName(Class<? extends CommandProcessor> processor) {
		this.processor = processor;
	}

	public CommandProcessor instantiate() throws InstantiationException, IllegalAccessException {
		return this.processor.newInstance();
	}
	public Command createCommand(BigData data) {
		return createCommand(data, new String[0]);
	}
	public Command createCommand(BigData data, String... parameters) {
		Command c = new Command().setParameters(parameters).setName(this);
		c.writeDataAsString(data);
		if(event) {
			return c.asEvent();
		}
		return c;
	}
	
	public Command createCommand(String... parameters) {
		Command c = new Command().setParameters(parameters).setName(this);
		if(event) {
			return c.asEvent();
		}
		return c;
	}
}
