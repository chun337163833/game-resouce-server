package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "enchantment_type", schema = "model")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "enchantmentTypeDescriptions", "itemEnchantments", "attributeType", "skillModel" })
public class EnchantmentType {
}