package org.shovelgame.game.domain.model;
import java.math.BigDecimal;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(identifierType = MinionTraitPK.class, versionField = "", table = "minion_trait", schema = "model", sequenceName = "model.minion_trait_id_seq")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionModel", "trait" })
public class MinionTrait {

    public BigDecimal getPower() {
        if (this.getOverridePower() == null || this.getOverridePower().intValue() == 0) {
            return this.getTrait().getPower();
        }
        return this.getOverridePower();
    }
}
