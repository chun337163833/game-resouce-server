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
import org.shovelgame.game.domain.i18n.TraitName;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.Trait;

privileged aspect Trait_Roo_DbManaged {
    
    @OneToMany(mappedBy = "trait")
    private Set<TraitDescription> Trait.traitDescriptions;
    
    @OneToMany(mappedBy = "trait")
    private Set<TraitName> Trait.traitNames;
    
    @OneToMany(mappedBy = "trait")
    private Set<MinionTrait> Trait.minionTraits;
    
    @ManyToOne
    @JoinColumn(name = "affected_attribute_type", referencedColumnName = "id")
    private AttributeType Trait.affectedAttributeType;
    
    @Column(name = "power", precision = 10, scale = 3)
    private BigDecimal Trait.power;
    
    @Column(name = "icon_name", length = 50)
    @NotNull
    private String Trait.iconName;
    
    @Column(name = "trait_id", length = 50, unique = true)
    @NotNull
    private String Trait.traitId;
    
    public Set<TraitDescription> Trait.getTraitDescriptions() {
        return traitDescriptions;
    }
    
    public void Trait.setTraitDescriptions(Set<TraitDescription> traitDescriptions) {
        this.traitDescriptions = traitDescriptions;
    }
    
    public Set<TraitName> Trait.getTraitNames() {
        return traitNames;
    }
    
    public void Trait.setTraitNames(Set<TraitName> traitNames) {
        this.traitNames = traitNames;
    }
    
    public Set<MinionTrait> Trait.getMinionTraits() {
        return minionTraits;
    }
    
    public void Trait.setMinionTraits(Set<MinionTrait> minionTraits) {
        this.minionTraits = minionTraits;
    }
    
    public AttributeType Trait.getAffectedAttributeType() {
        return affectedAttributeType;
    }
    
    public void Trait.setAffectedAttributeType(AttributeType affectedAttributeType) {
        this.affectedAttributeType = affectedAttributeType;
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
    
    public String Trait.getTraitId() {
        return traitId;
    }
    
    public void Trait.setTraitId(String traitId) {
        this.traitId = traitId;
    }
    
}
