// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.game.domain.model.SeekerSpecialization;

privileged aspect SeekerSpecialization_Roo_Jpa_Entity {
    
    declare @type: SeekerSpecialization: @Entity;
    
    declare @type: SeekerSpecialization: @Table(schema = "model", name = "seeker_specialization");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long SeekerSpecialization.id;
    
    public Long SeekerSpecialization.getId() {
        return this.id;
    }
    
    public void SeekerSpecialization.setId(Long id) {
        this.id = id;
    }
    
}