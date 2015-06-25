package org.shovelgame.game.domain.data;
import java.util.Set;
import javax.persistence.OneToOne;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "team", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "missions", "leader", "minionTop", "minionMid", "minionBot", "owner", "players" })
public class Team {

    @OneToOne(mappedBy = "team")
    private Player owner;

    public Set<Player> getPlayers() {
        throw new UnsupportedOperationException("Method not supported. Call getOwner() instead of");
    }

    public Minion getMinionByPosition(MinionPosition position) {
        if (MinionPosition.Leader.equals(position)) {
            return this.getLeader();
        } else if (MinionPosition.Bot.equals(position)) {
            return this.getMinionBot();
        } else if (MinionPosition.Mid.equals(position)) {
            return this.getMinionMid();
        } else if (MinionPosition.Top.equals(position)) {
            return this.getMinionTop();
        }
        throw new IllegalStateException(String.format("Minion on position %s missing", position.name()));
    }
}
