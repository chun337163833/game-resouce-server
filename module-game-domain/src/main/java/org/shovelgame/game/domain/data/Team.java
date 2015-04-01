package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "missions", "hero", "minionTop", "minionMid", "minionBot" })
@RooJpaActiveRecord(versionField = "", table = "team", schema = "data", sequenceName = "data.team_id_seq")
public class Team {
}
