package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "player", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "minions", "teams", "heroes" })
public class Player {
}
