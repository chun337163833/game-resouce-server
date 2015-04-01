// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.i18n.SpecializationDescription;
import org.shovelgame.game.domain.model.SeekerModel;
import org.shovelgame.game.domain.model.SeekerSpecialization;

privileged aspect SeekerSpecialization_Roo_DbManaged {
    
    @OneToMany(mappedBy = "seekerSpecialization")
    private Set<SpecializationDescription> SeekerSpecialization.specializationDescriptions;
    
    @ManyToOne
    @JoinColumn(name = "seeker_model", referencedColumnName = "id", nullable = false)
    private SeekerModel SeekerSpecialization.seekerModel;
    
    public Set<SpecializationDescription> SeekerSpecialization.getSpecializationDescriptions() {
        return specializationDescriptions;
    }
    
    public void SeekerSpecialization.setSpecializationDescriptions(Set<SpecializationDescription> specializationDescriptions) {
        this.specializationDescriptions = specializationDescriptions;
    }
    
    public SeekerModel SeekerSpecialization.getSeekerModel() {
        return seekerModel;
    }
    
    public void SeekerSpecialization.setSeekerModel(SeekerModel seekerModel) {
        this.seekerModel = seekerModel;
    }
    
}
