package org.shovelgame.game.domain.i18n;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "lang", "mission" })
@RooJpaActiveRecord(versionField = "", table = "mission_description", schema = "i18n", sequenceName = "i18n.mission_description_id_seq")
public class MissionDescription {
}
