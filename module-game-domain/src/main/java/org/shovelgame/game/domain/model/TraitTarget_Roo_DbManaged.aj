// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.TraitTarget;

privileged aspect TraitTarget_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "trait", referencedColumnName = "id", nullable = false)
    private MinionTrait TraitTarget.trait;
    
    public MinionTrait TraitTarget.getTrait() {
        return trait;
    }
    
    public void TraitTarget.setTrait(MinionTrait trait) {
        this.trait = trait;
    }
    
}
