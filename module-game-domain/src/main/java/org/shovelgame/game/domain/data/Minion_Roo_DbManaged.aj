// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.data.Minion;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.model.MinionModel;

privileged aspect Minion_Roo_DbManaged {
    
    @OneToMany(mappedBy = "minionTop")
    private Set<Team> Minion.teams;
    
    @OneToMany(mappedBy = "minionMid")
    private Set<Team> Minion.teams1;
    
    @OneToMany(mappedBy = "minionBot")
    private Set<Team> Minion.teams2;
    
    @ManyToOne
    @JoinColumn(name = "minion_model", referencedColumnName = "id", nullable = false)
    private MinionModel Minion.minionModel;
    
    @Column(name = "owner")
    private Long Minion.owner;
    
    @Column(name = "position", length = 50)
    private String Minion.position;
    
    @Column(name = "level")
    @NotNull
    private Integer Minion.level;
    
    public Set<Team> Minion.getTeams() {
        return teams;
    }
    
    public void Minion.setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    
    public Set<Team> Minion.getTeams1() {
        return teams1;
    }
    
    public void Minion.setTeams1(Set<Team> teams1) {
        this.teams1 = teams1;
    }
    
    public Set<Team> Minion.getTeams2() {
        return teams2;
    }
    
    public void Minion.setTeams2(Set<Team> teams2) {
        this.teams2 = teams2;
    }
    
    public MinionModel Minion.getMinionModel() {
        return minionModel;
    }
    
    public void Minion.setMinionModel(MinionModel minionModel) {
        this.minionModel = minionModel;
    }
    
    public Long Minion.getOwner() {
        return owner;
    }
    
    public void Minion.setOwner(Long owner) {
        this.owner = owner;
    }
    
    public String Minion.getPosition() {
        return position;
    }
    
    public void Minion.setPosition(String position) {
        this.position = position;
    }
    
    public Integer Minion.getLevel() {
        return level;
    }
    
    public void Minion.setLevel(Integer level) {
        this.level = level;
    }
    
}
