package org.shovelgame.domain.test;


import com.shovelgame.domain.model.Attribute;
import com.shovelgame.domain.model.HeroModel;
import com.shovelgame.domain.model.ItemEnchantment;
import com.shovelgame.domain.model.ItemModel;
import com.shovelgame.domain.model.MinionTraitPK;

public class TestDomainStability {

	public void isStable() {
		ItemModel item = new ItemModel();
		ItemEnchantment enc = new ItemEnchantment();
		Attribute att = new Attribute();
		HeroModel model = new HeroModel();
	}
}
