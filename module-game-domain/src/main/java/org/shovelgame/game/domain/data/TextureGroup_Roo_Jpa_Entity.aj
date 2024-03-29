// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shovelgame.game.domain.data.TextureGroup;

privileged aspect TextureGroup_Roo_Jpa_Entity {
    
    declare @type: TextureGroup: @Entity;
    
    declare @type: TextureGroup: @Table(schema = "data", name = "texture_group");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 100)
    private String TextureGroup.id;
    
    public String TextureGroup.getId() {
        return this.id;
    }
    
    public void TextureGroup.setId(String id) {
        this.id = id;
    }
    
}
