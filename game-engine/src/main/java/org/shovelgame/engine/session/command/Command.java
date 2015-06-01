package org.shovelgame.engine.session.command;

public class Command {

	private Long skill;
	private String source;
	private String destination;

	public Long getSkill() {
		return skill;
	}

	public void setSkill(Long skill) {
		this.skill = skill;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public static Command fromString(String json) {
		return null;
	}
	
	public String toJson() {
		return "";
	}
	
}
