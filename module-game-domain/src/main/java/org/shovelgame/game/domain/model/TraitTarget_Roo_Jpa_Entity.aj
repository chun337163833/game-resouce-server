// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.game.domain.model.TraitTarget;

privileged aspect TraitTarget_Roo_Jpa_Entity {
    
    declare @type: TraitTarget: @Entity;
    
    declare @type: TraitTarget: @Table(schema = "model", name = "trait_target");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long TraitTarget.id;
    
    public Long TraitTarget.getId() {
        return this.id;
    }
    
    public void TraitTarget.setId(Long id) {
        this.id = id;
    }
    
}
