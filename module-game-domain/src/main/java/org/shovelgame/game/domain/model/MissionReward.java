package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(inheritanceType = "SINGLE_TABLE", versionField = "", table = "mission_reward", schema = "model", sequenceName = "model.mission_reward_id_seq")
@RooToString(excludeFields = { "mission", "item", "minion" })
public abstract class MissionReward {
}
