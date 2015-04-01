package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "enchantmentType", "itemModel" })
@RooJpaActiveRecord(versionField = "", table = "item_enchantment", schema = "model", sequenceName = "model.item_enchantment_id_seq")
public class ItemEnchantment {
}
