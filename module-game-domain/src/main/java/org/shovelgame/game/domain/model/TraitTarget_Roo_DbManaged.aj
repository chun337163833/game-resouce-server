// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.model.Trait;
import org.shovelgame.game.domain.model.TraitTarget;

privileged aspect TraitTarget_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "trait", referencedColumnName = "id", nullable = false)
    private Trait TraitTarget.trait;
    
    @Column(name = "position", length = 50)
    @NotNull
    private String TraitTarget.position;
    
    public Trait TraitTarget.getTrait() {
        return trait;
    }
    
    public void TraitTarget.setTrait(Trait trait) {
        this.trait = trait;
    }
    
    public String TraitTarget.getPosition() {
        return position;
    }
    
    public void TraitTarget.setPosition(String position) {
        this.position = position;
    }
    
}
