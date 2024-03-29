// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.shovelgame.game.domain.i18n.EnchantmentTypeDescription;
import org.shovelgame.game.domain.i18n.Language;
import org.shovelgame.game.domain.model.EnchantmentType;

privileged aspect EnchantmentTypeDescription_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "lang", referencedColumnName = "id")
    private Language EnchantmentTypeDescription.lang;
    
    @ManyToOne
    @JoinColumn(name = "enchantment_type", referencedColumnName = "id", nullable = false)
    private EnchantmentType EnchantmentTypeDescription.enchantmentType;
    
    @Column(name = "value")
    private String EnchantmentTypeDescription.value;
    
    public Language EnchantmentTypeDescription.getLang() {
        return lang;
    }
    
    public void EnchantmentTypeDescription.setLang(Language lang) {
        this.lang = lang;
    }
    
    public EnchantmentType EnchantmentTypeDescription.getEnchantmentType() {
        return enchantmentType;
    }
    
    public void EnchantmentTypeDescription.setEnchantmentType(EnchantmentType enchantmentType) {
        this.enchantmentType = enchantmentType;
    }
    
    public String EnchantmentTypeDescription.getValue() {
        return value;
    }
    
    public void EnchantmentTypeDescription.setValue(String value) {
        this.value = value;
    }
    
}
