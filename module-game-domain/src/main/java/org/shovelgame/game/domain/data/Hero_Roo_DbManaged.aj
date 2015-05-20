// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.data.Hero;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.HeroModel;

privileged aspect Hero_Roo_DbManaged {
    
    @OneToMany(mappedBy = "hero")
    private Set<Team> Hero.teams;
    
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Player Hero.owner;
    
    @ManyToOne
    @JoinColumn(name = "hero_model", referencedColumnName = "id", nullable = false)
    private HeroModel Hero.heroModel;
    
    @Column(name = "level")
    @NotNull
    private Integer Hero.level;
    
    public Set<Team> Hero.getTeams() {
        return teams;
    }
    
    public void Hero.setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    
    public Player Hero.getOwner() {
        return owner;
    }
    
    public void Hero.setOwner(Player owner) {
        this.owner = owner;
    }
    
    public HeroModel Hero.getHeroModel() {
        return heroModel;
    }
    
    public void Hero.setHeroModel(HeroModel heroModel) {
        this.heroModel = heroModel;
    }
    
    public Integer Hero.getLevel() {
        return level;
    }
    
    public void Hero.setLevel(Integer level) {
        this.level = level;
    }
    
}
