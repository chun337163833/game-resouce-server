package org.shovelgame.engine.session.command;

import java.io.IOException;

import org.shovelgame.annotation.Logger;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Logger
public class Command {

	private CommandName name;
	private CommandStatus status = CommandStatus.Ok;
	private String[] parameters = new String[0];
	
	/**
	 * read only property used for sending big data to client
	 */
	private String data;

	public CommandName getName() {
		return name;
	}

	public Command setName(CommandName name) {
		this.name = name;
		return this;
	}

	public String[] getParameters() {
		return parameters;
	}

	public Command setParameters(String[] parameters) {
		this.parameters = parameters;
		return this;
	}

	public void emptyParameters() {
		this.parameters = new String[0];
	}

	public CommandStatus getStatus() {
		return status;
	}

	public Command setStatus(CommandStatus status) {
		this.status = status;
		return this;
	}

	public static Command fromString(String json) throws JsonParseException,
			JsonMappingException, IOException {
		return new ObjectMapper().readValue(json, Command.class);
	}

	public String toJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}

	public void validate() throws CommandException {
		try {
			Assert.notNull(this.status);
			Assert.notNull(this.name);
//			Assert.notNull(this.parameters);
		} catch (Exception e) {
			throw new CommandException("Command is not valid.", e);
		}
	}
	
	@JsonProperty("data")
	public String getData() {
		return data;
	}

	@JsonIgnore
	public void setData(String data) {
		this.data = data;
	}

	public void writeDataAsString(BigData data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.setData(mapper.writeValueAsString(data));
		} catch (JsonProcessingException e) {
			log.error("", e);
			this.setStatus(CommandStatus.Error);
		}
	}
	
}
