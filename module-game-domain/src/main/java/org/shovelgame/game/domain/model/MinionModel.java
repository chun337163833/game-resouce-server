package org.shovelgame.game.domain.model;
import java.util.Set;
import javax.persistence.OneToMany;
import org.shovelgame.game.domain.Attribute;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "minion_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minions", "minionSkills", "minionTraits", "attributes", "qualityGrade", "minionAttributes" })
public class MinionModel {

    @OneToMany(mappedBy = "minionModel", targetEntity = MinionAttribute.class)
    private Set<Attribute> minionAttributes;
}
