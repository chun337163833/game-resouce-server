// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nex.game.domain;

import com.nex.game.domain.Item;
import javax.persistence.Entity;
import javax.persistence.Table;

privileged aspect Item_Roo_Jpa_Entity {
    
    declare @type: Item: @Entity;
    
    declare @type: Item: @Table(name = "g_item");
    
}
