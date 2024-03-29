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
import org.shovelgame.game.domain.model.Skill;

privileged aspect Skill_Roo_Jpa_Entity {
    
    declare @type: Skill: @Entity;
    
    declare @type: Skill: @Table(schema = "model", name = "skill");
    
    @Id
    @SequenceGenerator(name = "skillGen", sequenceName = "model.skill_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "skillGen")
    @Column(name = "id")
    private Long Skill.id;
    
    public Long Skill.getId() {
        return this.id;
    }
    
    public void Skill.setId(Long id) {
        this.id = id;
    }
    
}
