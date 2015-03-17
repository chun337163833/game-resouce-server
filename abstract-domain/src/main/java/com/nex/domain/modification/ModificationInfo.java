package com.nex.domain.modification;

import java.util.Calendar;

public interface ModificationInfo {
	void setCreatedOn(Calendar c);
	void setModifiedOn(Calendar c);
	void setCreatedBy(Long userId);
	void setModifiedBy(Long userId);
}
