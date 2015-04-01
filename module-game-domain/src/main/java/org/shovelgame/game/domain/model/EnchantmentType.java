package org.shovelgame.game.domain.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "enchantmentTypeDescriptions", "itemEnchantments", "attributeType", "skillModel" })
@RooJpaActiveRecord(versionField = "", table = "enchantment_type", schema = "model", sequenceName = "model.enchantment_type_id_seq")
public class EnchantmentType {
}
