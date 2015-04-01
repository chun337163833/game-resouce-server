// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.model.HeroModel;
import org.shovelgame.game.domain.model.HeroSkill;
import org.shovelgame.game.domain.model.Skill;

privileged aspect HeroSkill_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "hero_model", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private HeroModel HeroSkill.heroModel;
    
    @ManyToOne
    @JoinColumn(name = "skill", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Skill HeroSkill.skill;
    
    @Column(name = "required_level")
    @NotNull
    private Integer HeroSkill.requiredLevel;
    
    @Column(name = "override_power", precision = 10, scale = 3)
    private BigDecimal HeroSkill.overridePower;
    
    public HeroModel HeroSkill.getHeroModel() {
        return heroModel;
    }
    
    public void HeroSkill.setHeroModel(HeroModel heroModel) {
        this.heroModel = heroModel;
    }
    
    public Skill HeroSkill.getSkill() {
        return skill;
    }
    
    public void HeroSkill.setSkill(Skill skill) {
        this.skill = skill;
    }
    
    public Integer HeroSkill.getRequiredLevel() {
        return requiredLevel;
    }
    
    public void HeroSkill.setRequiredLevel(Integer requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
    
    public BigDecimal HeroSkill.getOverridePower() {
        return overridePower;
    }
    
    public void HeroSkill.setOverridePower(BigDecimal overridePower) {
        this.overridePower = overridePower;
    }
    
}
