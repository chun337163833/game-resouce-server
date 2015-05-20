package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "hero", schema = "data", sequenceName = "data.hero_id_seq")
@RooToString(excludeFields = { "team", "heroModel", "teams", "owner" })
public class Hero {
}
