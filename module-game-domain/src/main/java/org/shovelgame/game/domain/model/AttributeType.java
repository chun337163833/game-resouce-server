package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import org.shovelgame.game.domain.enumaration.AttributeManagedType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "attribute_type", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeTypeDescriptions", "attributes", "enchantmentTypes", "itemModels", "skills", "traits", "heroAttributes", "minionAttributes" })
public class AttributeType {

    @Id
    @Column(name = "id", length = 50)
    @Enumerated
    private AttributeManagedType id;
}
