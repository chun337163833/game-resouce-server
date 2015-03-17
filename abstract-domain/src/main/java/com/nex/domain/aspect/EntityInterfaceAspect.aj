package com.nex.domain.aspect;

import com.nex.domain.annotation.EntityInterface;
import com.nex.domain.common.Entity;

public aspect EntityInterfaceAspect {
	declare parents: @EntityInterface * implements Entity;
}
