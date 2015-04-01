package org.shovelgame.jackson.serialization;

import java.io.IOException;
import java.lang.reflect.Field;

import org.shovelgame.utils.ReflectionUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class OrdinalEnumDeserializer extends JsonDeserializer<Enum<?>> {
	
	@Override
	public Enum<?> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String fieldName = jp.getCurrentName();
		Field enumField = ReflectionUtils.findField(jp.getCurrentValue().getClass(), fieldName);
		Class<?> type = enumField.getType();
		try {
			Enum<?>[] values = (Enum<?>[]) ReflectionUtils.findMethod(type, "values").invoke(null, new Object[0]);
			return values[jp.getNumberValue().intValue()];
		} catch (Exception e) {
			throw new RuntimeException("Cannot invoke method vaules() of type " + type.getSimpleName(), e);
		}
	}	
}
