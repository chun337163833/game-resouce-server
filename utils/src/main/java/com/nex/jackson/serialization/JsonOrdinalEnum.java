package com.nex.jackson.serialization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JsonSerialize(using=OrdinalEmumSerializer.class)
@JsonDeserialize(using=OrdinalEnumDeserializer.class)
public @interface JsonOrdinalEnum {

}
