package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "team", "minionModel", "teams", "teams1", "teams2" })
@RooJpaActiveRecord(versionField = "", table = "minion", schema = "data", sequenceName = "data.minion_id_seq")
public class Minion {
}
