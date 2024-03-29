package org.shovelgame.game.domain.data;
import org.shovelgame.game.domain.leveling.Levelable;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "minion", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "teams", "teams1", "teams2", "teams3", "owner", "minionModel" })
@Levelable(service = "highExperience")
public class Minion {

    public double getBoostExperience() {
        return getOwner().getCurrentExperienceBoost();
    }
}
