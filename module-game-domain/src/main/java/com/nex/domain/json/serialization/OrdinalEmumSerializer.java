package com.nex.domain.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class OrdinalEmumSerializer extends JsonSerializer<Enum<?>> {

	@Override
	public void serialize(Enum<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeNumber(value.ordinal());
	}

}
