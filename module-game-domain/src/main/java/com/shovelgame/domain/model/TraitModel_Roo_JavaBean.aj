// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.model;

import com.shovelgame.domain.model.TraitModel;
import com.shovelgame.enumaration.SkillAlgorithm;
import com.shovelgame.enumaration.TraitAlgorithm;

privileged aspect TraitModel_Roo_JavaBean {
    
    public TraitAlgorithm TraitModel.getAlg() {
        return this.alg;
    }
    
    public void TraitModel.setAlg(TraitAlgorithm alg) {
        this.alg = alg;
    }
    
    public SkillAlgorithm TraitModel.getAffectedSkillAlg() {
        return this.affectedSkillAlg;
    }
    
    public void TraitModel.setAffectedSkillAlg(SkillAlgorithm affectedSkillAlg) {
        this.affectedSkillAlg = affectedSkillAlg;
    }
    
}
