package com.nex.domain.injection;

import com.nex.domain.common.Entity;

public aspect EntityInterfaceAspect {
	declare parents: @EntityInterface * implements Entity;
}
