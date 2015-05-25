package org.shovelgame.game.domain.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
//@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(inheritanceType = "SINGLE_TABLE", versionField = "", table = "mission_reward", schema = "model", sequenceName = "model.mission_reward_id_seq")
@RooToString(excludeFields = { "mission", "item", "minion", "seeker" })
@DiscriminatorColumn(name = "dtype")
// @MappedSuperclass
public abstract class MissionReward {

    @ManyToOne
    @JoinColumn(name = "mission", referencedColumnName = "id", nullable = false)
    private Mission mission;

    @Column(name = "chance", precision = 3, scale = 2)
    private BigDecimal chance;
}
