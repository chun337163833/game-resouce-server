// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.i18n.AttributeTypeDescription;
import org.shovelgame.game.domain.i18n.EnchantmentTypeDescription;
import org.shovelgame.game.domain.i18n.ItemModelDescription;
import org.shovelgame.game.domain.i18n.Language;
import org.shovelgame.game.domain.i18n.MissionDescription;
import org.shovelgame.game.domain.i18n.SkillDescription;
import org.shovelgame.game.domain.i18n.SkillName;
import org.shovelgame.game.domain.i18n.TraitDescription;
import org.shovelgame.game.domain.i18n.TraitName;

privileged aspect Language_Roo_DbManaged {
    
    @OneToMany(mappedBy = "lang")
    private Set<AttributeTypeDescription> Language.attributeTypeDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<EnchantmentTypeDescription> Language.enchantmentTypeDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<ItemModelDescription> Language.itemModelDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<MissionDescription> Language.missionDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<SkillDescription> Language.skillDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<SkillName> Language.skillNames;
    
    @OneToMany(mappedBy = "lang")
    private Set<TraitDescription> Language.traitDescriptions;
    
    @OneToMany(mappedBy = "lang")
    private Set<TraitName> Language.traitNames;
    
    @Column(name = "description", length = 50)
    @NotNull
    private String Language.description;
    
    public Set<AttributeTypeDescription> Language.getAttributeTypeDescriptions() {
        return attributeTypeDescriptions;
    }
    
    public void Language.setAttributeTypeDescriptions(Set<AttributeTypeDescription> attributeTypeDescriptions) {
        this.attributeTypeDescriptions = attributeTypeDescriptions;
    }
    
    public Set<EnchantmentTypeDescription> Language.getEnchantmentTypeDescriptions() {
        return enchantmentTypeDescriptions;
    }
    
    public void Language.setEnchantmentTypeDescriptions(Set<EnchantmentTypeDescription> enchantmentTypeDescriptions) {
        this.enchantmentTypeDescriptions = enchantmentTypeDescriptions;
    }
    
    public Set<ItemModelDescription> Language.getItemModelDescriptions() {
        return itemModelDescriptions;
    }
    
    public void Language.setItemModelDescriptions(Set<ItemModelDescription> itemModelDescriptions) {
        this.itemModelDescriptions = itemModelDescriptions;
    }
    
    public Set<MissionDescription> Language.getMissionDescriptions() {
        return missionDescriptions;
    }
    
    public void Language.setMissionDescriptions(Set<MissionDescription> missionDescriptions) {
        this.missionDescriptions = missionDescriptions;
    }
    
    public Set<SkillDescription> Language.getSkillDescriptions() {
        return skillDescriptions;
    }
    
    public void Language.setSkillDescriptions(Set<SkillDescription> skillDescriptions) {
        this.skillDescriptions = skillDescriptions;
    }
    
    public Set<SkillName> Language.getSkillNames() {
        return skillNames;
    }
    
    public void Language.setSkillNames(Set<SkillName> skillNames) {
        this.skillNames = skillNames;
    }
    
    public Set<TraitDescription> Language.getTraitDescriptions() {
        return traitDescriptions;
    }
    
    public void Language.setTraitDescriptions(Set<TraitDescription> traitDescriptions) {
        this.traitDescriptions = traitDescriptions;
    }
    
    public Set<TraitName> Language.getTraitNames() {
        return traitNames;
    }
    
    public void Language.setTraitNames(Set<TraitName> traitNames) {
        this.traitNames = traitNames;
    }
    
    public String Language.getDescription() {
        return description;
    }
    
    public void Language.setDescription(String description) {
        this.description = description;
    }
    
}
