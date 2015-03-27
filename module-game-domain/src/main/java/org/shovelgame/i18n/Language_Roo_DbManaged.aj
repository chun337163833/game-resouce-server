// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.i18n;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.i18n.HeroTypeDescription;
import org.shovelgame.i18n.Language;
import org.shovelgame.i18n.SpecializationDesc;

privileged aspect Language_Roo_DbManaged {
    
    @OneToMany(mappedBy = "lang")
    private Set<HeroTypeDescription> Language.heroTypeDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<SpecializationDesc> Language.specializationDescs;
    
    @Column(name = "description", length = 50)
    @NotNull
    private String Language.description;
    
    public Set<HeroTypeDescription> Language.getHeroTypeDescriptions() {
        return heroTypeDescriptions;
    }
    
    public void Language.setHeroTypeDescriptions(Set<HeroTypeDescription> heroTypeDescriptions) {
        this.heroTypeDescriptions = heroTypeDescriptions;
    }
    
    public Set<SpecializationDesc> Language.getSpecializationDescs() {
        return specializationDescs;
    }
    
    public void Language.setSpecializationDescs(Set<SpecializationDesc> specializationDescs) {
        this.specializationDescs = specializationDescs;
    }
    
    public String Language.getDescription() {
        return description;
    }
    
    public void Language.setDescription(String description) {
        this.description = description;
    }
    
}
