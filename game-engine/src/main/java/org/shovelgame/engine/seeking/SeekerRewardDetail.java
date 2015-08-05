package org.shovelgame.engine.seeking;

import org.shovelgame.game.domain.enumeration.SeekerSpecializationType;

public class SeekerRewardDetail {

	SeekerSpecializationType type;

	/**
	 * value is based on type. If its gold or diamonds then value is how much
	 * resource is obtained, if its item, minion or seeker value value is id of
	 * persistent object
	 */
	private long value;

	public SeekerRewardDetail(SeekerSpecializationType type, long value) {
		super();
		this.type = type;
		this.value = value;
	}

	public SeekerSpecializationType getType() {
		return type;
	}

	public long getValue() {
		return value;
	}
}
