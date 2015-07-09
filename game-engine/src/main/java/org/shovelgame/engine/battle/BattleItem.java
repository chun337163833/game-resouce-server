package org.shovelgame.engine.battle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.enumeration.ItemType;
import org.shovelgame.game.domain.model.ItemEnchantment;

public class BattleItem {

	private AttributeManagedType attribute;
	private BigDecimal value;
	private ItemType type;
	private BattleEnchantment[] enchantments;
	
	public BattleItem(Item item) {
		this.attribute = item.getItemModel().getAttributeType().getId();
		this.value = item.getPower();
		this.type = item.getItemModel().getType();
		List<BattleEnchantment> ench = new ArrayList<>();
		item.getActiveEnchantments().forEach((ItemEnchantment e) -> ench.add(new BattleEnchantment(item, e)));
		this.enchantments = ench.toArray(new BattleEnchantment[ench.size()]);
	}

	public AttributeManagedType getAttribute() {
		return attribute;
	}

	public BigDecimal getValue() {
		return value;
	}

	public ItemType getType() {
		return type;
	}
	
	public BattleEnchantment[] getEnchantments() {
		return enchantments;
	}
	
	public boolean hasSkillEnchantment() {
		for(BattleEnchantment enchant: this.enchantments) {
			if(enchant.getSkillId() != null) {
				return true;
			}
		}
		return false;
	}
	
	public BigDecimal update(AttributeManagedType type, BigDecimal value) {
		BigDecimal val = value;
		if(attribute.equals(type)) {
			val = val.add(this.value);
		}
		for(BattleEnchantment enchant: this.enchantments) {
			if(enchant.getAttribute() != null && enchant.getAttribute().equals(type)){
				val = val.add(enchant.getValue());
			}
		}
		return val;
	}
	
}
