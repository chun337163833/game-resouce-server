package org.shovelgame.game.domain.data;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "texture", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "minionModels", "textureGroup", "version" })
@NamedQueries({ 
	@NamedQuery(name = "findTexturesGreaterThenVersion", query = "select e from Texture e where e.textureGroup.id = :group and e.version.value > :version"),
})
public class Texture {

    public static List<Texture> findTexturesGreaterThenVersion(BigDecimal version, String group) {
        TypedQuery<Texture> query = entityManager().createNamedQuery("findTexturesGreaterThenVersion", Texture.class);
        query.setParameter("version", version);
        query.setParameter("group", group);
        return query.getResultList();
    }
    
}
