// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.i18n;

import com.shovelgame.domain.i18n.Language;
import com.shovelgame.domain.i18n.TraitModelDescription;
import com.shovelgame.domain.model.TraitModel;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

privileged aspect TraitModelDescription_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "lang", referencedColumnName = "id", nullable = false)
    private Language TraitModelDescription.lang;
    
    @ManyToOne
    @JoinColumn(name = "trait_model", referencedColumnName = "id", nullable = false)
    private TraitModel TraitModelDescription.traitModel;
    
    @Column(name = "value")
    @NotNull
    private String TraitModelDescription.value;
    
    public Language TraitModelDescription.getLang() {
        return lang;
    }
    
    public void TraitModelDescription.setLang(Language lang) {
        this.lang = lang;
    }
    
    public TraitModel TraitModelDescription.getTraitModel() {
        return traitModel;
    }
    
    public void TraitModelDescription.setTraitModel(TraitModel traitModel) {
        this.traitModel = traitModel;
    }
    
    public String TraitModelDescription.getValue() {
        return value;
    }
    
    public void TraitModelDescription.setValue(String value) {
        this.value = value;
    }
    
}
