package org.shovelgame.domain.aspect;

import org.shovelgame.domain.annotation.EntityInterface;
import org.shovelgame.domain.common.Entity;

public aspect EntityInterfaceAspect {
	declare parents: @EntityInterface * implements Entity;
}
