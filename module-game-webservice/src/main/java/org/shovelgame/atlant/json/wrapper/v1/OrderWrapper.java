package org.shovelgame.atlant.json.wrapper.v1;

import java.util.Calendar;

import org.shovelgame.domain.CapacityType;
import org.shovelgame.domain.common.JsonObject;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperProperty;
import org.shovelgame.web.spring.ws.wrapper.convertor.annotation.WrapperRelation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderWrapper implements JsonObject {

	@JsonIgnore
	private Long id;

	@WrapperProperty("boardingCoordinate.longitude")
	private Double longitudeStart;

	@WrapperProperty("boardingCoordinate.latitude")
	private Double latitudeStart;

	@WrapperProperty("exitCoordinate.latitude")
	private Double longitudeEnd;

	@WrapperProperty("exitCoordinate.latitude")
	private Double latitudeEnd;

	private Calendar pickupTime;

	@WrapperRelation
	private DriverWrapper driver;

	private CapacityType capacityType;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public Double getLongitudeStart() {
		return longitudeStart;
	}

	public void setLongitudeStart(Double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}

	public Double getLatitudeStart() {
		return latitudeStart;
	}

	public void setLatitudeStart(Double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public Double getLongitudeEnd() {
		return longitudeEnd;
	}

	public void setLongitudeEnd(Double longitudeEnd) {
		this.longitudeEnd = longitudeEnd;
	}

	public Double getLatitudeEnd() {
		return latitudeEnd;
	}

	public void setLatitudeEnd(Double latitudeEnd) {
		this.latitudeEnd = latitudeEnd;
	}

	public Calendar getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Calendar pickupTime) {
		this.pickupTime = pickupTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CapacityType getCapacityType() {
		return capacityType;
	}

	public void setCapacityType(CapacityType capacityType) {
		this.capacityType = capacityType;
	}

	public DriverWrapper getDriver() {
		return driver;
	}

	public void setDriver(DriverWrapper driver) {
		this.driver = driver;
	}

}
