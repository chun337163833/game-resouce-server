// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.tmobile.domain.sms;

import com.tmobile.domain.sms.OutgoingSmsState;
import com.tmobile.domain.sms.SmsMessage;
import java.util.Calendar;

privileged aspect SmsMessage_Roo_JavaBean {
    
    public Long SmsMessage.getId() {
        return this.id;
    }
    
    public void SmsMessage.setId(Long id) {
        this.id = id;
    }
    
    public Long SmsMessage.getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void SmsMessage.setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String SmsMessage.getMessage() {
        return this.message;
    }
    
    public void SmsMessage.setMessage(String message) {
        this.message = message;
    }
    
    public OutgoingSmsState SmsMessage.getState() {
        return this.state;
    }
    
    public void SmsMessage.setState(OutgoingSmsState state) {
        this.state = state;
    }
    
    public Calendar SmsMessage.getCreationTime() {
        return this.creationTime;
    }
    
    public void SmsMessage.setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }
    
}
