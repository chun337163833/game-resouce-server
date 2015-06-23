package org.shovelgame.engine.session.command;

public class ErrorMessageCommand extends Command {

	public static ErrorMessageCommand fromOriginalCommand(Command command) {
		return fromOriginalCommand(command, "Unknown");
	}
	public static ErrorMessageCommand fromOriginalCommand(Command command, String message) {
		ErrorMessageCommand c = new ErrorMessageCommand();
		c.setName(command.getName());
		c.setStatus(CommandStatus.Error);
		c.setMessage(message);
		return c;
	}
	
	
	public void setMessage(String message) {
		this.setParameters(new String[]{message});
	}
}
