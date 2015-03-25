package org.shovelgame.domain;

import org.shovelgame.jackson.serialization.JsonOrdinalEnum;

@JsonOrdinalEnum
public enum OrderState {
	NEW, ACCEPTED, ASSIGNED, DRIVERACCEPTED, ONWAY, WAITINGFORCUSTOMER, DRIVE, FINISHED, CANCELLED
}
