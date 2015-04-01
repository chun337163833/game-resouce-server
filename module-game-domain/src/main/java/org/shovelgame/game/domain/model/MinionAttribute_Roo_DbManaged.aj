// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.MinionAttribute;
import org.shovelgame.game.domain.model.MinionModel;

privileged aspect MinionAttribute_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id", nullable = false)
    private AttributeType MinionAttribute.type;
    
    @ManyToOne
    @JoinColumn(name = "minion_model", referencedColumnName = "id", nullable = false)
    private MinionModel MinionAttribute.minionModel;
    
    @Column(name = "value", precision = 10, scale = 3)
    @NotNull
    private BigDecimal MinionAttribute.value;
    
    public AttributeType MinionAttribute.getType() {
        return type;
    }
    
    public void MinionAttribute.setType(AttributeType type) {
        this.type = type;
    }
    
    public MinionModel MinionAttribute.getMinionModel() {
        return minionModel;
    }
    
    public void MinionAttribute.setMinionModel(MinionModel minionModel) {
        this.minionModel = minionModel;
    }
    
    public BigDecimal MinionAttribute.getValue() {
        return value;
    }
    
    public void MinionAttribute.setValue(BigDecimal value) {
        this.value = value;
    }
    
}