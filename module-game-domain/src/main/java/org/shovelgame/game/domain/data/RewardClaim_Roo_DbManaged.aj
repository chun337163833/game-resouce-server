// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.RewardClaim;
import org.shovelgame.game.domain.model.MissionReward;

privileged aspect RewardClaim_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "player", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Player RewardClaim.player;
    
    @ManyToOne
    @JoinColumn(name = "reward", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MissionReward RewardClaim.reward;
    
    public Player RewardClaim.getPlayer() {
        return player;
    }
    
    public void RewardClaim.setPlayer(Player player) {
        this.player = player;
    }
    
    public MissionReward RewardClaim.getReward() {
        return reward;
    }
    
    public void RewardClaim.setReward(MissionReward reward) {
        this.reward = reward;
    }
    
}