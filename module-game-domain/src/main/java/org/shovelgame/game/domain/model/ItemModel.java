package org.shovelgame.game.domain.model;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import org.shovelgame.game.domain.enumeration.ItemType;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "items", "itemModelDescriptions", "itemEnchantments", "attributeType", "qualityGrade" })
@RooJpaActiveRecord(versionField = "", table = "item_model", schema = "model", sequenceName = "model.item_model_id_seq")
public class ItemModel {

    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ItemType type;
}
