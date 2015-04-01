// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.model;

import com.shovelgame.domain.model.Attribute;
import com.shovelgame.domain.model.HeroModel;
import com.shovelgame.domain.model.HeroSkill;
import com.shovelgame.domain.model.HeroTrait;
import com.shovelgame.domain.model.HeroType;
import com.shovelgame.domain.model.QualityGrade;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect HeroModel_Roo_DbManaged {
    
    @OneToMany(mappedBy = "heroModel")
    private Set<HeroSkill> HeroModel.heroSkills;
    
    @OneToMany(mappedBy = "heroModel")
    private Set<HeroTrait> HeroModel.heroTraits;
    
    @ManyToOne
    @JoinColumn(name = "attributes", referencedColumnName = "id", nullable = false)
    private Attribute HeroModel.attributes;
    
    @ManyToOne
    @JoinColumn(name = "hero_type", referencedColumnName = "id", nullable = false)
    private HeroType HeroModel.heroType;
    
    @ManyToOne
    @JoinColumn(name = "quality_grade", referencedColumnName = "id", nullable = false)
    private QualityGrade HeroModel.qualityGrade;
    
    @Column(name = "name", length = 50)
    @NotNull
    private String HeroModel.name;
    
    @Column(name = "image_bundle_name", length = 50)
    @NotNull
    private String HeroModel.imageBundleName;
    
    public Set<HeroSkill> HeroModel.getHeroSkills() {
        return heroSkills;
    }
    
    public void HeroModel.setHeroSkills(Set<HeroSkill> heroSkills) {
        this.heroSkills = heroSkills;
    }
    
    public Set<HeroTrait> HeroModel.getHeroTraits() {
        return heroTraits;
    }
    
    public void HeroModel.setHeroTraits(Set<HeroTrait> heroTraits) {
        this.heroTraits = heroTraits;
    }
    
    public Attribute HeroModel.getAttributes() {
        return attributes;
    }
    
    public void HeroModel.setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }
    
    public HeroType HeroModel.getHeroType() {
        return heroType;
    }
    
    public void HeroModel.setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }
    
    public QualityGrade HeroModel.getQualityGrade() {
        return qualityGrade;
    }
    
    public void HeroModel.setQualityGrade(QualityGrade qualityGrade) {
        this.qualityGrade = qualityGrade;
    }
    
    public String HeroModel.getName() {
        return name;
    }
    
    public void HeroModel.setName(String name) {
        this.name = name;
    }
    
    public String HeroModel.getImageBundleName() {
        return imageBundleName;
    }
    
    public void HeroModel.setImageBundleName(String imageBundleName) {
        this.imageBundleName = imageBundleName;
    }
    
}
