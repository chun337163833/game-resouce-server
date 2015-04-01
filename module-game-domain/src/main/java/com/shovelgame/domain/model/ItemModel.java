package com.shovelgame.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.shovelgame.enumaration.ItemType;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "item_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "itemModelDescriptions", "itemEnchantments", "attributeType", "qualityGrade" })
public class ItemModel {

    @Column(name = "type", length = 10)
    @NotNull
    @Enumerated
    private ItemType type;
}
