// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.tmobile.domain.smspayment;

import com.tmobile.domain.smspayment.SmsPayment;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

privileged aspect SmsPayment_Roo_Jpa_Entity {
    
    declare @type: SmsPayment: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer SmsPayment.version;
    
    public Integer SmsPayment.getVersion() {
        return this.version;
    }
    
    public void SmsPayment.setVersion(Integer version) {
        this.version = version;
    }
    
}
