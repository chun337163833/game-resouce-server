package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "mission", schema = "model", sequenceName = "model.mission_id_seq")
@RooToString(excludeFields = { "missionDescriptions", "missionRewards", "team", "missionRewardMinions", "missionRewardItems" })
public class Mission {
}
