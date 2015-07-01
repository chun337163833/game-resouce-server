// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.i18n.SkillDescription;
import org.shovelgame.game.domain.i18n.SkillName;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.EnchantmentType;
import org.shovelgame.game.domain.model.MinionSkill;
import org.shovelgame.game.domain.model.Skill;

privileged aspect Skill_Roo_DbManaged {
    
    @OneToMany(mappedBy = "skill")
    private Set<SkillDescription> Skill.skillDescriptions;
    
    @OneToMany(mappedBy = "skill")
    private Set<SkillName> Skill.skillNames;
    
    @OneToMany(mappedBy = "skillModel")
    private Set<EnchantmentType> Skill.enchantmentTypes;
    
    @OneToMany(mappedBy = "skill")
    private Set<MinionSkill> Skill.minionSkills;
    
    @ManyToOne
    @JoinColumn(name = "attribute_type", referencedColumnName = "id")
    private AttributeType Skill.attributeType;
    
    @Column(name = "icon_name", length = 50)
    @NotNull
    private String Skill.iconName;
    
    @Column(name = "power", precision = 10, scale = 3)
    private BigDecimal Skill.power;
    
    @Column(name = "cooldown")
    @NotNull
    private Integer Skill.cooldown;
    
    @Column(name = "ticks")
    private Integer Skill.ticks;
    
    @Column(name = "skill_id", length = 100, unique = true)
    @NotNull
    private String Skill.skillId;
    
    public Set<SkillDescription> Skill.getSkillDescriptions() {
        return skillDescriptions;
    }
    
    public void Skill.setSkillDescriptions(Set<SkillDescription> skillDescriptions) {
        this.skillDescriptions = skillDescriptions;
    }
    
    public Set<SkillName> Skill.getSkillNames() {
        return skillNames;
    }
    
    public void Skill.setSkillNames(Set<SkillName> skillNames) {
        this.skillNames = skillNames;
    }
    
    public Set<EnchantmentType> Skill.getEnchantmentTypes() {
        return enchantmentTypes;
    }
    
    public void Skill.setEnchantmentTypes(Set<EnchantmentType> enchantmentTypes) {
        this.enchantmentTypes = enchantmentTypes;
    }
    
    public Set<MinionSkill> Skill.getMinionSkills() {
        return minionSkills;
    }
    
    public void Skill.setMinionSkills(Set<MinionSkill> minionSkills) {
        this.minionSkills = minionSkills;
    }
    
    public AttributeType Skill.getAttributeType() {
        return attributeType;
    }
    
    public void Skill.setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }
    
    public String Skill.getIconName() {
        return iconName;
    }
    
    public void Skill.setIconName(String iconName) {
        this.iconName = iconName;
    }
    
    public BigDecimal Skill.getPower() {
        return power;
    }
    
    public void Skill.setPower(BigDecimal power) {
        this.power = power;
    }
    
    public Integer Skill.getCooldown() {
        return cooldown;
    }
    
    public void Skill.setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
    
    public Integer Skill.getTicks() {
        return ticks;
    }
    
    public void Skill.setTicks(Integer ticks) {
        this.ticks = ticks;
    }
    
    public String Skill.getSkillId() {
        return skillId;
    }
    
    public void Skill.setSkillId(String skillId) {
        this.skillId = skillId;
    }
    
}
