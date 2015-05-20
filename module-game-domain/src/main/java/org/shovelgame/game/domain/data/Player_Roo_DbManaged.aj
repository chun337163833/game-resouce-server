// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.data.Hero;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Team;

privileged aspect Player_Roo_DbManaged {
    
    @OneToMany(mappedBy = "owner")
    private Set<Hero> Player.heroes;
    
    @OneToMany(mappedBy = "owner")
    private Set<Item> Player.items;
    
    @OneToMany(mappedBy = "owner")
    private Set<Minion> Player.minions;
    
    @OneToMany(mappedBy = "owner")
    private Set<Team> Player.teams;
    
    public Set<Hero> Player.getHeroes() {
        return heroes;
    }
    
    public void Player.setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }
    
    public Set<Item> Player.getItems() {
        return items;
    }
    
    public void Player.setItems(Set<Item> items) {
        this.items = items;
    }
    
    public Set<Minion> Player.getMinions() {
        return minions;
    }
    
    public void Player.setMinions(Set<Minion> minions) {
        this.minions = minions;
    }
    
    public Set<Team> Player.getTeams() {
        return teams;
    }
    
    public void Player.setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    
}
