// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.data.Hero;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.Mission;

privileged aspect Team_Roo_DbManaged {
    
    @OneToMany(mappedBy = "team")
    private Set<Item> Team.items;
    
    @OneToMany(mappedBy = "team")
    private Set<Mission> Team.missions;
    
    @ManyToOne
    @JoinColumn(name = "hero", referencedColumnName = "id", nullable = false)
    private Hero Team.hero;
    
    @ManyToOne
    @JoinColumn(name = "minion_top", referencedColumnName = "id", nullable = false)
    private Minion Team.minionTop;
    
    @ManyToOne
    @JoinColumn(name = "minion_mid", referencedColumnName = "id", nullable = false)
    private Minion Team.minionMid;
    
    @ManyToOne
    @JoinColumn(name = "minion_bot", referencedColumnName = "id", nullable = false)
    private Minion Team.minionBot;
    
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Player Team.owner;
    
    public Set<Item> Team.getItems() {
        return items;
    }
    
    public void Team.setItems(Set<Item> items) {
        this.items = items;
    }
    
    public Set<Mission> Team.getMissions() {
        return missions;
    }
    
    public void Team.setMissions(Set<Mission> missions) {
        this.missions = missions;
    }
    
    public Hero Team.getHero() {
        return hero;
    }
    
    public void Team.setHero(Hero hero) {
        this.hero = hero;
    }
    
    public Minion Team.getMinionTop() {
        return minionTop;
    }
    
    public void Team.setMinionTop(Minion minionTop) {
        this.minionTop = minionTop;
    }
    
    public Minion Team.getMinionMid() {
        return minionMid;
    }
    
    public void Team.setMinionMid(Minion minionMid) {
        this.minionMid = minionMid;
    }
    
    public Minion Team.getMinionBot() {
        return minionBot;
    }
    
    public void Team.setMinionBot(Minion minionBot) {
        this.minionBot = minionBot;
    }
    
    public Player Team.getOwner() {
        return owner;
    }
    
    public void Team.setOwner(Player owner) {
        this.owner = owner;
    }
    
}
