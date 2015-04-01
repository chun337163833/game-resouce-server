package org.shovelgame.aspect;

import javax.persistence.Transient;

import org.shovelgame.annotation.Logger;
import org.slf4j.LoggerFactory;



public aspect LoggerAspect {
public interface LoggerInjection {}
	
	@Transient
	public transient org.slf4j.Logger LoggerInjection.log = LoggerFactory.getLogger(getClass());
	
	declare parents: @Logger * implements LoggerInjection;
}
