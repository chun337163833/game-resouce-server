package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeTypeDescriptions", "attributes", "enchantmentTypes", "itemModels", "skills", "traits", "heroAttributes", "minionAttributes" })
@RooJpaActiveRecord(versionField = "", table = "attribute_type", schema = "model", sequenceName = "mode.attribute_type_id_seq")
public class AttributeType {

    @Id
    @Column(name = "id", length = 50)
    @Enumerated(EnumType.STRING)
    private AttributeManagedType id;
}
