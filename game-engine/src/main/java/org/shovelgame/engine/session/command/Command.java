package org.shovelgame.engine.session.command;

import java.io.IOException;

import org.shovelgame.annotation.Logger;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Logger
public class Command {

	private CommandName name;
	private CommandStatus status = CommandStatus.Ok;
	private boolean response;
	private boolean event;
	private String[] parameters = new String[0];

	public Command() {
		// TODO Auto-generated constructor stub
	}

	public Command(boolean response) {
		super();
		this.response = response;
	}

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

	public static Command fromString(String json) throws IOException, JsonParseException, JsonMappingException {
		if(StringUtils.isEmpty(json)) {
			return null;
		}
		return new ObjectMapper().readValue(json, Command.class);
	}

	public String toJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}

	public void validate() throws CommandException {
		try {
			Assert.notNull(this.status);
			Assert.notNull(this.name);
			// Assert.notNull(this.parameters);
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
		mapper.setVisibilityChecker(mapper.getSerializationConfig()
				.getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			this.setData(mapper.writeValueAsString(data));
		} catch (JsonProcessingException e) {
			log.error("", e);
			this.setStatus(CommandStatus.Error);
		}
	}

	public boolean isResponse() {
		return response;
	}
	public boolean isEvent() {
		return event;
	}

	public Command asEvent() {
		this.event = true;
		return this;
	}
	
	public Command asResponse() {
		this.response = true;
		return this;
	}
	
}
