// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.game.domain.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.shovelgame.game.domain.model.Skill;

privileged aspect Skill_Roo_ToString {
    
    public String Skill.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("skillDescriptions", "skillNames", "enchantmentTypes", "minionSkills", "attributeType").toString();
    }
    
}
