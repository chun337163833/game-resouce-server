// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import org.shovelgame.domain.Tariff;

privileged aspect Tariff_Roo_JavaBean {
    
    public Long Tariff.getId() {
        return this.id;
    }
    
    public void Tariff.setId(Long id) {
        this.id = id;
    }
    
    public String Tariff.getTariffName() {
        return this.tariffName;
    }
    
    public void Tariff.setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
    
    public Integer Tariff.getDiscount() {
        return this.discount;
    }
    
    public void Tariff.setDiscount(Integer discount) {
        this.discount = discount;
    }
    
    public Boolean Tariff.getBasicDiscount() {
        return this.basicDiscount;
    }
    
    public void Tariff.setBasicDiscount(Boolean basicDiscount) {
        this.basicDiscount = basicDiscount;
    }
    
}
