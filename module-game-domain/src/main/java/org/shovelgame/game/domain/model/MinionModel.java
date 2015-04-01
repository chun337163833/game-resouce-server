package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.Attribute;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits", "attributes", "qualityGrade", "minionAttributes" })
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model", sequenceName = "model.minion_model_id_seq")
public class MinionModel {

    @OneToMany(mappedBy = "minionModel", targetEntity = MinionAttribute.class)
    private Set<Attribute> minionAttributes;
}
