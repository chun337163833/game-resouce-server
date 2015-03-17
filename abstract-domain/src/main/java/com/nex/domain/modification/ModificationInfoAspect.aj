package com.nex.domain.modification;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public aspect ModificationInfoAspect {
	
	
	@Transient
	@Resource(name="userService")
	@JsonIgnore
	private UserService ModificationInfo.uService;
	
	@Transient
	@JsonIgnore
	private Long ModificationInfo.createdBy;

	@Transient
	@JsonIgnore
	private Long ModificationInfo.modifiedBy;

	@Transient
	@JsonIgnore
	private Calendar ModificationInfo.modifiedOn;

	@Transient
	@JsonIgnore
	private Calendar ModificationInfo.createdOn;

	declare parents: @Modifiable * implements ModificationInfo;
	declare parents: @ModifiableWithCustomListener * implements ModificationInfo;

	@Column(name = "MODIFIED_ON")
	@Access(AccessType.PROPERTY)
	public Calendar ModificationInfo.getModifiedOn() {
		return modifiedOn;
	}

	@Column(name = "CREATED_ON")
	@Access(AccessType.PROPERTY)
	public Calendar ModificationInfo.getCreatedOn() {
		return createdOn;
	}
	@Column(name = "MODIFIED_BY")
	@Access(AccessType.PROPERTY)
	public Long ModificationInfo.getModifiedBy() {
		return this.modifiedBy;
	}

	@Column(name = "CREATED_BY")
	@Access(AccessType.PROPERTY)
	public Long ModificationInfo.getCreatedBy() {
		return createdBy;
	}

	public void ModificationInfo.setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void ModificationInfo.setModifiedOn(Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void ModificationInfo.setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public void ModificationInfo.setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@JsonIgnore
	public User ModificationInfo.getModifiedByUser() {
		if(this.modifiedBy == null) return null;
		return this.uService.findById(this.modifiedBy);
	}
	@JsonIgnore
	public User ModificationInfo.getCreatedByUser() {
		if(this.createdBy == null) return null;
		return this.uService.findById(this.createdBy);
	}
}
