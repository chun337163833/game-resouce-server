package org.shovelgame.domain.common;


public interface Entity {

	Object getId();
	Entity merge();
	void persist();
	void flush();
	void remove();
	
}
