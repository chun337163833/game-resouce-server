// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.model.Attributes;
import org.shovelgame.model.Grade;
import org.shovelgame.model.MinionModel;

privileged aspect MinionModel_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "id", nullable = false)
    private Attributes MinionModel.attributeId;
    
    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id", nullable = false)
    private Grade MinionModel.gradeId;
    
    @Column(name = "name", length = 50)
    @NotNull
    private String MinionModel.name;
    
    public Attributes MinionModel.getAttributeId() {
        return attributeId;
    }
    
    public void MinionModel.setAttributeId(Attributes attributeId) {
        this.attributeId = attributeId;
    }
    
    public Grade MinionModel.getGradeId() {
        return gradeId;
    }
    
    public void MinionModel.setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }
    
    public String MinionModel.getName() {
        return name;
    }
    
    public void MinionModel.setName(String name) {
        this.name = name;
    }
    
}
