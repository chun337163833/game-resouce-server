// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model.reward;

import org.shovelgame.game.domain.model.ItemModel;
import org.shovelgame.game.domain.model.reward.ItemReward;

privileged aspect ItemReward_Roo_JavaBean {
    
    public ItemModel ItemReward.getItem() {
        return this.item;
    }
    
    public void ItemReward.setItem(ItemModel item) {
        this.item = item;
    }
    
}
