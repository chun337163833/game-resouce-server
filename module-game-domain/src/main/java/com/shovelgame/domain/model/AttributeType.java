package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.AttributeManagedType;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "attribute_type", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "attributeTypeDescriptions", "attributeses", "enchantmentTypes", "attributes", "itemModels" })
public class AttributeType {

    @Id
    @Column(name = "id", length = 10)
    private AttributeManagedType id;
}
