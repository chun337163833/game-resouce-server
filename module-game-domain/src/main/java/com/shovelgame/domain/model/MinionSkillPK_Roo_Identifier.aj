// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.model;

import com.shovelgame.domain.model.MinionSkillPK;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

privileged aspect MinionSkillPK_Roo_Identifier {
    
    declare @type: MinionSkillPK: @Embeddable;
    
    @Column(name = "minion_model", nullable = false)
    private Long MinionSkillPK.minionModel;
    
    @Column(name = "skill_model", nullable = false)
    private Long MinionSkillPK.skillModel;
    
    @Column(name = "required_level", nullable = false)
    private Integer MinionSkillPK.requiredLevel;
    
    @Column(name = "override_power", nullable = false, precision = 10, scale = 3)
    private BigDecimal MinionSkillPK.overridePower;
    
    public MinionSkillPK.new(Long minionModel, Long skillModel, Integer requiredLevel, BigDecimal overridePower) {
        super();
        this.minionModel = minionModel;
        this.skillModel = skillModel;
        this.requiredLevel = requiredLevel;
        this.overridePower = overridePower;
    }

    private MinionSkillPK.new() {
        super();
    }

    public Long MinionSkillPK.getMinionModel() {
        return minionModel;
    }
    
    public Long MinionSkillPK.getSkillModel() {
        return skillModel;
    }
    
    public Integer MinionSkillPK.getRequiredLevel() {
        return requiredLevel;
    }
    
    public BigDecimal MinionSkillPK.getOverridePower() {
        return overridePower;
    }
    
}
