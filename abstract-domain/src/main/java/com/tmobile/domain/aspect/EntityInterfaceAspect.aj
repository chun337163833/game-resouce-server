package com.tmobile.domain.aspect;

import com.tmobile.domain.annotation.EntityInterface;
import com.tmobile.domain.common.Entity;

public aspect EntityInterfaceAspect {
	declare parents: @EntityInterface * implements Entity;
}
