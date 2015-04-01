package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "attribute_type", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeTypeDescriptions", "attributes", "enchantmentTypes", "itemModels", "skills", "traits" })
public class AttributeType {
}