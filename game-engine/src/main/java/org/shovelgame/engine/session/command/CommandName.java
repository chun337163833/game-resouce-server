package org.shovelgame.engine.session.command;

import org.shovelgame.engine.session.command.impl.KillCommand;
import org.shovelgame.engine.session.command.impl.MatchmakingCommand;
import org.shovelgame.engine.session.command.impl.MissionCommand;
import org.shovelgame.engine.session.command.impl.SyncTeamCommand;
import org.shovelgame.environment.Environment;
import org.shovelgame.environment.EnvironmentType;

public enum CommandName {
	
	
	Authentication(null),
	Matchmaking(MatchmakingCommand.class),
	Mission(MissionCommand.class),
	UseSkill(null),
	SyncTeam(SyncTeamCommand.class),
	TestKill(KillCommand.class),
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
