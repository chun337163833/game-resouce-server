package org.shovelgame.game.domain.i18n;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "language", schema = "i18n")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeTypeDescriptions", "enchantmentTypeDescriptions", "itemModelDescriptions", "minionSpecializationDescriptions", "missionDescriptions", "seekerSpecializationDescriptions", "skillDescriptions", "skillNames", "traitDescriptions", "traitNames" })
public class Language {
}
