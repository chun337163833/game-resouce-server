// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.model;

import com.shovelgame.domain.model.HeroSkillPK;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

privileged aspect HeroSkillPK_Roo_Identifier {
    
    declare @type: HeroSkillPK: @Embeddable;
    
    @Column(name = "hero_model", nullable = false)
    private Long HeroSkillPK.heroModel;
    
    @Column(name = "skill_model", nullable = false)
    private Long HeroSkillPK.skillModel;
    
    @Column(name = "required_level", nullable = false)
    private Integer HeroSkillPK.requiredLevel;
    
    @Column(name = "override_power", nullable = false, precision = 10, scale = 3)
    private BigDecimal HeroSkillPK.overridePower;
    
    public HeroSkillPK.new(Long heroModel, Long skillModel, Integer requiredLevel, BigDecimal overridePower) {
        super();
        this.heroModel = heroModel;
        this.skillModel = skillModel;
        this.requiredLevel = requiredLevel;
        this.overridePower = overridePower;
    }

    private HeroSkillPK.new() {
        super();
    }

    public Long HeroSkillPK.getHeroModel() {
        return heroModel;
    }
    
    public Long HeroSkillPK.getSkillModel() {
        return skillModel;
    }
    
    public Integer HeroSkillPK.getRequiredLevel() {
        return requiredLevel;
    }
    
    public BigDecimal HeroSkillPK.getOverridePower() {
        return overridePower;
    }
    
}
