package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "mission", schema = "model", sequenceName = "model.mission_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "missionDescriptions", "missionRewards", "team" })
public class Mission {
}