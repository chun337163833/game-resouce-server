// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.shovelgame.domain.model;

import com.shovelgame.domain.model.HeroModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

privileged aspect HeroModel_Roo_ToString {
    
    public String HeroModel.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("attributeId", "gradeId", "heroType", "attributes", "qualityGrade", "heroSkills", "heroTraits").toString();
    }
    
}
