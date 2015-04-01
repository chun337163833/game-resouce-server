// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.shovelgame.game.domain.model.MinionModel;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.Trait;

privileged aspect MinionTrait_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "minion_model", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MinionModel MinionTrait.minionModel;
    
    @ManyToOne
    @JoinColumn(name = "trait", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Trait MinionTrait.trait;
    
    public MinionModel MinionTrait.getMinionModel() {
        return minionModel;
    }
    
    public void MinionTrait.setMinionModel(MinionModel minionModel) {
        this.minionModel = minionModel;
    }
    
    public Trait MinionTrait.getTrait() {
        return trait;
    }
    
    public void MinionTrait.setTrait(Trait trait) {
        this.trait = trait;
    }
    
}