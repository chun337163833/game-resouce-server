package com.tmobile.domain.modification;

import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configurable
public class ModificationInfoListener {
	
	@Resource(name="userService")
	private UserService uService;
	
	@PrePersist
	public void modifyWhenCreating(ModificationInfo info) {
		info.setCreatedOn(new GregorianCalendar());
		Long cl = this.getCurrentUser();
		info.setCreatedBy(cl);
		info.setModifiedOn(new GregorianCalendar());
		info.setModifiedBy(cl);
	}

	@PreUpdate
	public void modifyWhenUpdating(ModificationInfo info) {
		info.setModifiedOn(new GregorianCalendar());
		info.setModifiedBy(this.getCurrentUser());
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long getCurrentUser() {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		try {
			return uService.findUserByName(user.getName()).getId();
		} catch (Exception ne) {
			LoggerFactory.getLogger(getClass()).warn("could not find user " + user.getName(), ne);
			return null;
		}
	}
}
