// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import org.shovelgame.domain.Car;

privileged aspect Car_Roo_Jpa_Entity {
    
    declare @type: Car: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer Car.version;
    
    public Integer Car.getVersion() {
        return this.version;
    }
    
    public void Car.setVersion(Integer version) {
        this.version = version;
    }
    
}