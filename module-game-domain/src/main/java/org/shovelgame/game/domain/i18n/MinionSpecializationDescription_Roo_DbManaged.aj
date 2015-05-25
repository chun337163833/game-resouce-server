// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.shovelgame.game.domain.i18n.Language;
import org.shovelgame.game.domain.i18n.MinionSpecializationDescription;
import org.shovelgame.game.domain.model.MinionSpecialization;

privileged aspect MinionSpecializationDescription_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "lang", referencedColumnName = "id", nullable = false)
    private Language MinionSpecializationDescription.lang;
    
    @ManyToOne
    @JoinColumn(name = "specialization", referencedColumnName = "id", nullable = false)
    private MinionSpecialization MinionSpecializationDescription.specialization;
    
    @Column(name = "text")
    @NotNull
    private String MinionSpecializationDescription.text;
    
    public Language MinionSpecializationDescription.getLang() {
        return lang;
    }
    
    public void MinionSpecializationDescription.setLang(Language lang) {
        this.lang = lang;
    }
    
    public MinionSpecialization MinionSpecializationDescription.getSpecialization() {
        return specialization;
    }
    
    public void MinionSpecializationDescription.setSpecialization(MinionSpecialization specialization) {
        this.specialization = specialization;
    }
    
    public String MinionSpecializationDescription.getText() {
        return text;
    }
    
    public void MinionSpecializationDescription.setText(String text) {
        this.text = text;
    }
    
}
