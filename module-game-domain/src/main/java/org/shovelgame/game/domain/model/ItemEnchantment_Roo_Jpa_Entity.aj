// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.game.domain.model.ItemEnchantment;

privileged aspect ItemEnchantment_Roo_Jpa_Entity {
    
    declare @type: ItemEnchantment: @Entity;
    
    declare @type: ItemEnchantment: @Table(schema = "model", name = "item_enchantment");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ItemEnchantment.id;
    
    public Long ItemEnchantment.getId() {
        return this.id;
    }
    
    public void ItemEnchantment.setId(Long id) {
        this.id = id;
    }
    
}