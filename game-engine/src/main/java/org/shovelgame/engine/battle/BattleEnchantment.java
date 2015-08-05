package org.shovelgame.engine.battle;

import java.math.BigDecimal;

import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.enumeration.AttributeManagedType;
import org.shovelgame.game.domain.model.AttributeType;
import org.shovelgame.game.domain.model.ItemEnchantment;
import org.shovelgame.game.domain.model.Skill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleEnchantment {
	
	@JsonIgnore
	private ItemEnchantment enchantment;
	private BigDecimal value;
	
	public BattleEnchantment(Item i, ItemEnchantment enchantment) {
		this.enchantment = enchantment;
		this.value = enchantment.getPower(i);
	}

	public BigDecimal getValue() {
		return value;
	}
	
	@JsonProperty("skillId")
	public String getSkillId() {
		Skill skill = enchantment.getEnchantmentType().getSkillModel();
		return skill == null? null: skill.getSkillId();
	}
	
	@JsonProperty("attribute")
	public AttributeManagedType getAttribute() {
		AttributeType attr = enchantment.getEnchantmentType().getAttributeType();
		return attr == null ? null : attr.getId();
	}
}
