package com.nex.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OrderStateDeserializer extends JsonDeserializer<String> {


	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return "";//OrderState.values()[jp.getNumberValue().intValue()];
	}

}
