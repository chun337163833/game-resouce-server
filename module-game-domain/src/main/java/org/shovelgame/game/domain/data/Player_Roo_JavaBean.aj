// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Team;

privileged aspect Player_Roo_JavaBean {
    
    public Team Player.getTeam() {
        return this.team;
    }
    
    public void Player.setTeam(Team team) {
        this.team = team;
    }
    
}