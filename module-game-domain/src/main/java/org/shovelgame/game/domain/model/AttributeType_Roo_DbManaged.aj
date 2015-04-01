// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.util.Set;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.i18n.AttributeTypeDescription;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.EnchantmentType;
import org.shovelgame.game.domain.model.HeroAttribute;
import org.shovelgame.game.domain.model.ItemModel;
import org.shovelgame.game.domain.model.MinionAttribute;
import org.shovelgame.game.domain.model.Skill;
import org.shovelgame.game.domain.model.Trait;

privileged aspect AttributeType_Roo_DbManaged {
    
    @OneToMany(mappedBy = "attributeType")
    private Set<AttributeTypeDescription> AttributeType.attributeTypeDescriptions;
    
    @OneToMany(mappedBy = "attributeType")
    private Set<EnchantmentType> AttributeType.enchantmentTypes;
    
    @OneToMany(mappedBy = "type")
    private Set<HeroAttribute> AttributeType.heroAttributes;
    
    @OneToMany(mappedBy = "attributeType")
    private Set<ItemModel> AttributeType.itemModels;
    
    @OneToMany(mappedBy = "type")
    private Set<MinionAttribute> AttributeType.minionAttributes;
    
    @OneToMany(mappedBy = "attributeType")
    private Set<Skill> AttributeType.skills;
    
    @OneToMany(mappedBy = "affectedAttributeType")
    private Set<Trait> AttributeType.traits;
    
    public Set<AttributeTypeDescription> AttributeType.getAttributeTypeDescriptions() {
        return attributeTypeDescriptions;
    }
    
    public void AttributeType.setAttributeTypeDescriptions(Set<AttributeTypeDescription> attributeTypeDescriptions) {
        this.attributeTypeDescriptions = attributeTypeDescriptions;
    }
    
    public Set<EnchantmentType> AttributeType.getEnchantmentTypes() {
        return enchantmentTypes;
    }
    
    public void AttributeType.setEnchantmentTypes(Set<EnchantmentType> enchantmentTypes) {
        this.enchantmentTypes = enchantmentTypes;
    }
    
    public Set<HeroAttribute> AttributeType.getHeroAttributes() {
        return heroAttributes;
    }
    
    public void AttributeType.setHeroAttributes(Set<HeroAttribute> heroAttributes) {
        this.heroAttributes = heroAttributes;
    }
    
    public Set<ItemModel> AttributeType.getItemModels() {
        return itemModels;
    }
    
    public void AttributeType.setItemModels(Set<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }
    
    public Set<MinionAttribute> AttributeType.getMinionAttributes() {
        return minionAttributes;
    }
    
    public void AttributeType.setMinionAttributes(Set<MinionAttribute> minionAttributes) {
        this.minionAttributes = minionAttributes;
    }
    
    public Set<Skill> AttributeType.getSkills() {
        return skills;
    }
    
    public void AttributeType.setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
    
    public Set<Trait> AttributeType.getTraits() {
        return traits;
    }
    
    public void AttributeType.setTraits(Set<Trait> traits) {
        this.traits = traits;
    }
    
}
