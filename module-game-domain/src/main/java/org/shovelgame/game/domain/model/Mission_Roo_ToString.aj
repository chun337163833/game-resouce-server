// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.shovelgame.game.domain.model.Mission;

privileged aspect Mission_Roo_ToString {
    
    public String Mission.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("missionDescriptions", "missionRewards", "team", "backgroung", "background").toString();
    }
    
}
