package org.shovelgame.engine.session.command;

import org.shovelgame.engine.session.command.impl.MatchmakingCommand;
import org.shovelgame.engine.session.command.impl.MissionCommand;

public enum CommandName {
	
	
	Authentication(null),
	Matchmaking(MatchmakingCommand.class),
	Mission(MissionCommand.class),
	UseSkill(null),
	;
	private Class<? extends CommandProcessor> processor;
	
	
	private CommandName(Class<? extends CommandProcessor> processor) {
		this.processor = processor;
	}

	public CommandProcessor instantiate() throws InstantiationException, IllegalAccessException {
		return this.processor.newInstance();
	}

	public Command createCommand(String... parameters) {
		return new Command().setParameters(parameters).setName(this);
	}
}
