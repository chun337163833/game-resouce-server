package org.shovelgame.domain;

import org.shovelgame.jackson.serialization.JsonOrdinalEnum;
import org.shovelgame.jackson.serialization.OrdinalEnumDeserializer;
import org.shovelgame.jackson.serialization.OrdinalEnumSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using=OrdinalEnumSerializer.class)
@JsonDeserialize(using=OrdinalEnumDeserializer.class)
public enum CapacityType {
	MAX4, C5TO6, C7TO8
}
