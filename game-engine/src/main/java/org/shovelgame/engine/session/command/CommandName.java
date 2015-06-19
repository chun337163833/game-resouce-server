package org.shovelgame.engine.session.command;

public enum CommandName {
	Authentication,
	Matchmaking;
	
	public Command createCommand(String... parameters) {
		return new Command().setParameters(parameters).setName(this);
	}
}
