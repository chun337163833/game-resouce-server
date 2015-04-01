package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumaration.ItemType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "item_model", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "itemModelDescriptions", "itemEnchantments", "attributeType", "qualityGrade" })
public class ItemModel {

    @Column(name = "type", length = 10)
    @Enumerated
    @NotNull
    private ItemType type;
}
