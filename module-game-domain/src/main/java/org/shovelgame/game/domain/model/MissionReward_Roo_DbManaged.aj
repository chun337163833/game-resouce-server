// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.model.ItemModel;
import org.shovelgame.game.domain.model.MinionModel;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;

privileged aspect MissionReward_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "id")
    private ItemModel MissionReward.item;
    
    @ManyToOne
    @JoinColumn(name = "minion", referencedColumnName = "id")
    private MinionModel MissionReward.minion;
    
    @ManyToOne
    @JoinColumn(name = "mission", referencedColumnName = "id", nullable = false)
    private Mission MissionReward.mission;
    
    @Column(name = "dtype", length = 100)
    @NotNull
    private String MissionReward.dtype;
    
    @Column(name = "chance", precision = 3, scale = 2)
    private BigDecimal MissionReward.chance;
    
    @Column(name = "gold")
    private Integer MissionReward.gold;
    
    @Column(name = "diamond")
    private Integer MissionReward.diamond;
    
    public ItemModel MissionReward.getItem() {
        return item;
    }
    
    public void MissionReward.setItem(ItemModel item) {
        this.item = item;
    }
    
    public MinionModel MissionReward.getMinion() {
        return minion;
    }
    
    public void MissionReward.setMinion(MinionModel minion) {
        this.minion = minion;
    }
    
    public Mission MissionReward.getMission() {
        return mission;
    }
    
    public void MissionReward.setMission(Mission mission) {
        this.mission = mission;
    }
    
    public String MissionReward.getDtype() {
        return dtype;
    }
    
    public void MissionReward.setDtype(String dtype) {
        this.dtype = dtype;
    }
    
    public BigDecimal MissionReward.getChance() {
        return chance;
    }
    
    public void MissionReward.setChance(BigDecimal chance) {
        this.chance = chance;
    }
    
    public Integer MissionReward.getGold() {
        return gold;
    }
    
    public void MissionReward.setGold(Integer gold) {
        this.gold = gold;
    }
    
    public Integer MissionReward.getDiamond() {
        return diamond;
    }
    
    public void MissionReward.setDiamond(Integer diamond) {
        this.diamond = diamond;
    }
    
}
