// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.i18n;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.game.domain.i18n.ItemModelDescription;

privileged aspect ItemModelDescription_Roo_Jpa_Entity {
    
    declare @type: ItemModelDescription: @Entity;
    
    declare @type: ItemModelDescription: @Table(schema = "i18n", name = "item_model_description");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ItemModelDescription.id;
    
    public Long ItemModelDescription.getId() {
        return this.id;
    }
    
    public void ItemModelDescription.setId(Long id) {
        this.id = id;
    }
    
}
