// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.shovelgame.game.domain.model.MinionSkillPK;

privileged aspect MinionSkillPK_Roo_Identifier {
    
    declare @type: MinionSkillPK: @Embeddable;
    
    @Column(name = "minion_model", nullable = false)
    private Long MinionSkillPK.minionModel;
    
    @Column(name = "skill", nullable = false)
    private Long MinionSkillPK.skill;
    
    @Column(name = "required_level", nullable = false)
    private Integer MinionSkillPK.requiredLevel;
    
    @Column(name = "override_power", nullable = false, precision = 10, scale = 3)
    private BigDecimal MinionSkillPK.overridePower;
    
    public MinionSkillPK.new(Long minionModel, Long skill, Integer requiredLevel, BigDecimal overridePower) {
        super();
        this.minionModel = minionModel;
        this.skill = skill;
        this.requiredLevel = requiredLevel;
        this.overridePower = overridePower;
    }

    private MinionSkillPK.new() {
        super();
    }

    public Long MinionSkillPK.getMinionModel() {
        return minionModel;
    }
    
    public Long MinionSkillPK.getSkill() {
        return skill;
    }
    
    public Integer MinionSkillPK.getRequiredLevel() {
        return requiredLevel;
    }
    
    public BigDecimal MinionSkillPK.getOverridePower() {
        return overridePower;
    }
    
}