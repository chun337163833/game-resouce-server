// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import org.shovelgame.domain.ChangeLog;
import org.shovelgame.domain.Order;

privileged aspect ChangeLog_Roo_JavaBean {
    
    public Long ChangeLog.getId() {
        return this.id;
    }
    
    public void ChangeLog.setId(Long id) {
        this.id = id;
    }
    
    public String ChangeLog.getOperation() {
        return this.operation;
    }
    
    public void ChangeLog.setOperation(String operation) {
        this.operation = operation;
    }
    
    public String ChangeLog.getAttributes() {
        return this.attributes;
    }
    
    public void ChangeLog.setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
    public Order ChangeLog.getOrder() {
        return this.order;
    }
    
    public void ChangeLog.setOrder(Order order) {
        this.order = order;
    }
    
}