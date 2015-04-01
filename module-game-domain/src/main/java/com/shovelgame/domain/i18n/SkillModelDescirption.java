package com.shovelgame.domain.i18n;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "skill_model_descirption", schema = "i18n")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "lang", "skillModel" })
public class SkillModelDescirption {
}
