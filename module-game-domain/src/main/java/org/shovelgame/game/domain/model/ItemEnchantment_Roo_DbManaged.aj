// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.model.EnchantmentType;
import org.shovelgame.game.domain.model.ItemEnchantment;
import org.shovelgame.game.domain.model.ItemModel;

privileged aspect ItemEnchantment_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "enchantment_type", referencedColumnName = "id", nullable = false)
    private EnchantmentType ItemEnchantment.enchantmentType;
    
    @ManyToOne
    @JoinColumn(name = "item_model", referencedColumnName = "id", nullable = false)
    private ItemModel ItemEnchantment.itemModel;
    
    @Column(name = "base_value", precision = 10, scale = 3)
    @NotNull
    private BigDecimal ItemEnchantment.baseValue;
    
    @Column(name = "required_enchant")
    @NotNull
    private Integer ItemEnchantment.requiredEnchant;
    
    public EnchantmentType ItemEnchantment.getEnchantmentType() {
        return enchantmentType;
    }
    
    public void ItemEnchantment.setEnchantmentType(EnchantmentType enchantmentType) {
        this.enchantmentType = enchantmentType;
    }
    
    public ItemModel ItemEnchantment.getItemModel() {
        return itemModel;
    }
    
    public void ItemEnchantment.setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
    
    public BigDecimal ItemEnchantment.getBaseValue() {
        return baseValue;
    }
    
    public void ItemEnchantment.setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }
    
    public Integer ItemEnchantment.getRequiredEnchant() {
        return requiredEnchant;
    }
    
    public void ItemEnchantment.setRequiredEnchant(Integer requiredEnchant) {
        this.requiredEnchant = requiredEnchant;
    }
    
}
