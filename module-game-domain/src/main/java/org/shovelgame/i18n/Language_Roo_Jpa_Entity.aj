// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.i18n;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.i18n.Language;

privileged aspect Language_Roo_Jpa_Entity {
    
    declare @type: Language: @Entity;
    
    declare @type: Language: @Table(schema = "i18n", name = "language");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 2)
    private String Language.id;
    
    public String Language.getId() {
        return this.id;
    }
    
    public void Language.setId(String id) {
        this.id = id;
    }
    
}
