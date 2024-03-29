// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.shovelgame.game.domain.model.MissionReward;

privileged aspect MissionReward_Roo_Jpa_Entity {
    
    declare @type: MissionReward: @Entity;
    
    declare @type: MissionReward: @Table(schema = "model", name = "mission_reward");
    
    declare @type: MissionReward: @Inheritance(strategy = InheritanceType.SINGLE_TABLE);
    
    @Id
    @SequenceGenerator(name = "missionRewardGen", sequenceName = "model.mission_reward_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "missionRewardGen")
    @Column(name = "id")
    private Long MissionReward.id;
    
    public Long MissionReward.getId() {
        return this.id;
    }
    
    public void MissionReward.setId(Long id) {
        this.id = id;
    }
    
}
