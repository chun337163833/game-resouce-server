// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.shovelgame.game.domain.model.HeroSkillPK;

privileged aspect HeroSkillPK_Roo_Identifier {
    
    declare @type: HeroSkillPK: @Embeddable;
    
    @Column(name = "hero_model", nullable = false)
    private Long HeroSkillPK.heroModel;
    
    @Column(name = "skill", nullable = false)
    private Long HeroSkillPK.skill;
    
    @Column(name = "required_level", nullable = false)
    private Integer HeroSkillPK.requiredLevel;
    
    @Column(name = "override_power", nullable = false, precision = 10, scale = 3)
    private BigDecimal HeroSkillPK.overridePower;
    
    public HeroSkillPK.new(Long heroModel, Long skill, Integer requiredLevel, BigDecimal overridePower) {
        super();
        this.heroModel = heroModel;
        this.skill = skill;
        this.requiredLevel = requiredLevel;
        this.overridePower = overridePower;
    }

    private HeroSkillPK.new() {
        super();
    }

    public Long HeroSkillPK.getHeroModel() {
        return heroModel;
    }
    
    public Long HeroSkillPK.getSkill() {
        return skill;
    }
    
    public Integer HeroSkillPK.getRequiredLevel() {
        return requiredLevel;
    }
    
    public BigDecimal HeroSkillPK.getOverridePower() {
        return overridePower;
    }
    
}