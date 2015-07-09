package org.shovelgame.game.domain.data;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.shovelgame.game.domain.model.ItemEnchantment;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "item", schema = "data")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "owner", "team", "itemModel" })
public class Item {

    public BigDecimal getPower() {
        return getPower(getItemModel().getValue());
    }

    public BigDecimal getPower(BigDecimal baseValue) {
        return baseValue.add(BigDecimal.valueOf(getEnchant()));
    }

    public Set<ItemEnchantment> getActiveEnchantments() {
        Set<ItemEnchantment> set = new HashSet<>();
        getItemModel().getItemEnchantments().forEach((ItemEnchantment e) -> {if(e.getRequiredEnchant() <= getEnchant()) {set.add(e);}});
        return set;
    }
}
