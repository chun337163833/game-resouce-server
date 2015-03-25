package com.tmobile.wrapper.conversion.entity;

public class EntityB {

	private String name;
	private NestedEntity entity;
	private NestedEntity entity2;
	private NestedEntity entity3;

	public NestedEntity getEntity3() {
		return entity3;
	}

	public void setEntity3(NestedEntity entity3) {
		this.entity3 = entity3;
	}

	public NestedEntity getEntity2() {
		return entity2;
	}

	public void setEntity2(NestedEntity entity2) {
		this.entity2 = entity2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NestedEntity getEntity() {
		return entity;
	}

	public void setEntity(NestedEntity entity) {
		this.entity = entity;
	}

}
