// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.i18n.TraitDescription;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.HeroTrait;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.Trait;
import org.shovelgame.game.domain.model.TraitTarget;

privileged aspect Trait_Roo_DbManaged {
    
    @OneToMany(mappedBy = "trait")
    private Set<TraitDescription> Trait.traitDescriptions;
    
    @OneToMany(mappedBy = "trait")
    private Set<HeroTrait> Trait.heroTraits;
    
    @OneToMany(mappedBy = "trait")
    private Set<MinionTrait> Trait.minionTraits;
    
    @OneToMany(mappedBy = "trait")
    private Set<TraitTarget> Trait.traitTargets;
    
    @ManyToOne
    @JoinColumn(name = "affected_attribute_type", referencedColumnName = "id")
    private AttributeType Trait.affectedAttributeType;
    
    @Column(name = "affected_skill_alg", length = 50)
    private String Trait.affectedSkillAlg;
    
    @Column(name = "alg", length = 50)
    @NotNull
    private String Trait.alg;
    
    @Column(name = "power", precision = 10)
    private BigDecimal Trait.power;
    
    @Column(name = "icon_name", length = 50)
    @NotNull
    private String Trait.iconName;
    
    public Set<TraitDescription> Trait.getTraitDescriptions() {
        return traitDescriptions;
    }
    
    public void Trait.setTraitDescriptions(Set<TraitDescription> traitDescriptions) {
        this.traitDescriptions = traitDescriptions;
    }
    
    public Set<HeroTrait> Trait.getHeroTraits() {
        return heroTraits;
    }
    
    public void Trait.setHeroTraits(Set<HeroTrait> heroTraits) {
        this.heroTraits = heroTraits;
    }
    
    public Set<MinionTrait> Trait.getMinionTraits() {
        return minionTraits;
    }
    
    public void Trait.setMinionTraits(Set<MinionTrait> minionTraits) {
        this.minionTraits = minionTraits;
    }
    
    public Set<TraitTarget> Trait.getTraitTargets() {
        return traitTargets;
    }
    
    public void Trait.setTraitTargets(Set<TraitTarget> traitTargets) {
        this.traitTargets = traitTargets;
    }
    
    public AttributeType Trait.getAffectedAttributeType() {
        return affectedAttributeType;
    }
    
    public void Trait.setAffectedAttributeType(AttributeType affectedAttributeType) {
        this.affectedAttributeType = affectedAttributeType;
    }
    
    public String Trait.getAffectedSkillAlg() {
        return affectedSkillAlg;
    }
    
    public void Trait.setAffectedSkillAlg(String affectedSkillAlg) {
        this.affectedSkillAlg = affectedSkillAlg;
    }
    
    public String Trait.getAlg() {
        return alg;
    }
    
    public void Trait.setAlg(String alg) {
        this.alg = alg;
    }
    
    public BigDecimal Trait.getPower() {
        return power;
    }
    
    public void Trait.setPower(BigDecimal power) {
        this.power = power;
    }
    
    public String Trait.getIconName() {
        return iconName;
    }
    
    public void Trait.setIconName(String iconName) {
        this.iconName = iconName;
    }
    
}