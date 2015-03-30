package com.shovelgame.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "item_enchantment", schema = "model")
@RooToString(excludeFields = { "enchantmentModel", "itemModel", "enchantmentType" })
public class ItemEnchantment {
}
