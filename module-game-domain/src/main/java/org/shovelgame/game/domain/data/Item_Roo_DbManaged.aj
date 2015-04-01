// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.ItemModel;

privileged aspect Item_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "team", referencedColumnName = "id")
    private Team Item.team;
    
    @ManyToOne
    @JoinColumn(name = "item_model", referencedColumnName = "id", nullable = false)
    private ItemModel Item.itemModel;
    
    @Column(name = "owner")
    private Long Item.owner;
    
    @Column(name = "enchant")
    private Integer Item.enchant;
    
    public Team Item.getTeam() {
        return team;
    }
    
    public void Item.setTeam(Team team) {
        this.team = team;
    }
    
    public ItemModel Item.getItemModel() {
        return itemModel;
    }
    
    public void Item.setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
    
    public Long Item.getOwner() {
        return owner;
    }
    
    public void Item.setOwner(Long owner) {
        this.owner = owner;
    }
    
    public Integer Item.getEnchant() {
        return enchant;
    }
    
    public void Item.setEnchant(Integer enchant) {
        this.enchant = enchant;
    }
    
}
