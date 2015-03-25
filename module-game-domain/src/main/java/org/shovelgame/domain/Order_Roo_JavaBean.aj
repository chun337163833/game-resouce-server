// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import org.shovelgame.domain.CapacityType;
import org.shovelgame.domain.Customer;
import org.shovelgame.domain.Driver;
import org.shovelgame.domain.GCoordinate;
import org.shovelgame.domain.Order;
import org.shovelgame.domain.OrderState;
import org.shovelgame.domain.PaymentType;

privileged aspect Order_Roo_JavaBean {
    
    public Long Order.getId() {
        return this.id;
    }
    
    public void Order.setId(Long id) {
        this.id = id;
    }
    
    public Calendar Order.getPickupTime() {
        return this.pickupTime;
    }
    
    public void Order.setPickupTime(Calendar pickupTime) {
        this.pickupTime = pickupTime;
    }
    
    public CapacityType Order.getCapacityType() {
        return this.capacityType;
    }
    
    public void Order.setCapacityType(CapacityType capacityType) {
        this.capacityType = capacityType;
    }
    
    public String Order.getBoardingPlace() {
        return this.boardingPlace;
    }
    
    public void Order.setBoardingPlace(String boardingPlace) {
        this.boardingPlace = boardingPlace;
    }
    
    public GCoordinate Order.getBoardingCoordinate() {
        return this.boardingCoordinate;
    }
    
    public void Order.setBoardingCoordinate(GCoordinate boardingCoordinate) {
        this.boardingCoordinate = boardingCoordinate;
    }
    
    public String Order.getExitPlace() {
        return this.exitPlace;
    }
    
    public void Order.setExitPlace(String exitPlace) {
        this.exitPlace = exitPlace;
    }
    
    public GCoordinate Order.getExitCoordinate() {
        return this.exitCoordinate;
    }
    
    public void Order.setExitCoordinate(GCoordinate exitCoordinate) {
        this.exitCoordinate = exitCoordinate;
    }
    
    public BigDecimal Order.getFixedPrice() {
        return this.fixedPrice;
    }
    
    public void Order.setFixedPrice(BigDecimal fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
    
    public BigDecimal Order.getDeliveryFee() {
        return this.deliveryFee;
    }
    
    public void Order.setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    
    public String Order.getNote() {
        return this.note;
    }
    
    public void Order.setNote(String note) {
        this.note = note;
    }
    
    public Boolean Order.getDisplayCustomer() {
        return this.displayCustomer;
    }
    
    public void Order.setDisplayCustomer(Boolean displayCustomer) {
        this.displayCustomer = displayCustomer;
    }
    
    public Boolean Order.getNoRegistrationOffer() {
        return this.noRegistrationOffer;
    }
    
    public void Order.setNoRegistrationOffer(Boolean noRegistrationOffer) {
        this.noRegistrationOffer = noRegistrationOffer;
    }
    
    public PaymentType Order.getPaymentType() {
        return this.paymentType;
    }
    
    public void Order.setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
    public OrderState Order.getOrderState() {
        return this.orderState;
    }
    
    public void Order.setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
    
    public Integer Order.getRouteLength() {
        return this.routeLength;
    }
    
    public void Order.setRouteLength(Integer routeLength) {
        this.routeLength = routeLength;
    }
    
    public BigDecimal Order.getFare() {
        return this.fare;
    }
    
    public void Order.setFare(BigDecimal fare) {
        this.fare = fare;
    }
    
    public String Order.getDriverNote() {
        return this.driverNote;
    }
    
    public void Order.setDriverNote(String driverNote) {
        this.driverNote = driverNote;
    }
    
    public Double Order.getApproxRouteLength() {
        return this.approxRouteLength;
    }
    
    public void Order.setApproxRouteLength(Double approxRouteLength) {
        this.approxRouteLength = approxRouteLength;
    }
    
    public Integer Order.getApproxRouteTimeInMin() {
        return this.approxRouteTimeInMin;
    }
    
    public void Order.setApproxRouteTimeInMin(Integer approxRouteTimeInMin) {
        this.approxRouteTimeInMin = approxRouteTimeInMin;
    }
    
    public BigDecimal Order.getApproxPrice() {
        return this.approxPrice;
    }
    
    public void Order.setApproxPrice(BigDecimal approxPrice) {
        this.approxPrice = approxPrice;
    }
    
    public Boolean Order.getArrivalSmsSent() {
        return this.arrivalSmsSent;
    }
    
    public void Order.setArrivalSmsSent(Boolean arrivalSmsSent) {
        this.arrivalSmsSent = arrivalSmsSent;
    }
    
    public Boolean Order.getBasicDiscountPaid() {
        return this.basicDiscountPaid;
    }
    
    public void Order.setBasicDiscountPaid(Boolean basicDiscountPaid) {
        this.basicDiscountPaid = basicDiscountPaid;
    }
    
    public Boolean Order.getStreetJob() {
        return this.streetJob;
    }
    
    public void Order.setStreetJob(Boolean streetJob) {
        this.streetJob = streetJob;
    }
    
    public Driver Order.getDriver() {
        return this.driver;
    }
    
    public void Order.setDriver(Driver driver) {
        this.driver = driver;
    }
    
    public Customer Order.getCustomer() {
        return this.customer;
    }
    
    public void Order.setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}