// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.shovelgame.game.domain.model.SeekerModel;

privileged aspect SeekerModel_Roo_Jpa_Entity {
    
    declare @type: SeekerModel: @Entity;
    
    declare @type: SeekerModel: @Table(schema = "model", name = "seeker_model");
    
    @Id
    @SequenceGenerator(name = "seekerModelGen", sequenceName = "model.seeker_model_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seekerModelGen")
    @Column(name = "id")
    private Long SeekerModel.id;
    
    public Long SeekerModel.getId() {
        return this.id;
    }
    
    public void SeekerModel.setId(Long id) {
        this.id = id;
    }
    
}
