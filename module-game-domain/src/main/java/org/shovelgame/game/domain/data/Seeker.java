package org.shovelgame.game.domain.data;
import java.util.Calendar;
import org.shovelgame.game.domain.leveling.Levelable;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "seeker", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "owner", "seekerModel" })
@Levelable
public class Seeker {

    public int getSearchTime() {
        return getSeekerModel().getRarity().getSeekerSearchTime();
    }

    public void sent() {
        this.setStartedSearchTime(Calendar.getInstance());
    }

    public Calendar getSearchTimeEnd() {
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(getStartedSearchTime().getTime());
        endTime.add(Calendar.HOUR, getSearchTime());
        return endTime;
    }
}
