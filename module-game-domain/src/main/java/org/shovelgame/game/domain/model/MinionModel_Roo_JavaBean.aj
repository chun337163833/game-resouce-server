// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.Set;
import org.shovelgame.game.domain.Attribute;
import org.shovelgame.game.domain.model.MinionModel;

privileged aspect MinionModel_Roo_JavaBean {
    
    public Set<Attribute> MinionModel.getMinionAttributes() {
        return this.minionAttributes;
    }
    
    public void MinionModel.setMinionAttributes(Set<Attribute> minionAttributes) {
        this.minionAttributes = minionAttributes;
    }
    
}
