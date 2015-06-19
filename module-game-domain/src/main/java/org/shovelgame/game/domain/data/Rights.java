package org.shovelgame.game.domain.data;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = RightsPK.class, versionField = "", table = "rights", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "player", "role" })
public class Rights {
}
