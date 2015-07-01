package org.shovelgame.engine.session.command;

import org.shovelgame.engine.session.command.impl.TestDamageCommand;
import org.shovelgame.engine.session.command.impl.TestKillCommand;
import org.shovelgame.engine.session.command.impl.MatchmakingCommand;
import org.shovelgame.engine.session.command.impl.MissionCommand;
import org.shovelgame.engine.session.command.impl.SyncTeamCommand;
import org.shovelgame.engine.session.command.impl.UseSkillCommand;

public enum CommandName {
	Authentication(null),
	Matchmaking(MatchmakingCommand.class),
	Mission(MissionCommand.class),
	UseSkill(UseSkillCommand.class),
	SyncTeam(SyncTeamCommand.class),
	TestKill(TestKillCommand.class),
	TestDamage(TestDamageCommand.class),
	EvtGameEnd(null)
	;
	
	private Class<? extends CommandProcessor> processor;
	
	
	private CommandName(Class<? extends CommandProcessor> processor) {
		this.processor = processor;
	}

	public CommandProcessor instantiate() throws InstantiationException, IllegalAccessException {
		return this.processor.newInstance();
	}

	public Command createCommand(BigData data) {
		Command c = new Command().setParameters(null).setName(this);
		c.writeDataAsString(data);
		return c;
	}
	
	public Command createCommand(String... parameters) {
		return new Command().setParameters(parameters).setName(this);
	}
}
