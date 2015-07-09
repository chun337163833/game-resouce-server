package org.shovelgame.engine.skill;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;

public interface TemporarySkill extends CountdownSkill {

	AttributeManagedType getAttribute();
}
