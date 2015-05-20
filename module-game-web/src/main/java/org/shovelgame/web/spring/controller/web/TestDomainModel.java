package org.shovelgame.web.spring.controller.web;

import org.shovelgame.game.domain.data.Hero;
import org.shovelgame.game.domain.data.Player;
import org.shovelgame.game.domain.enumeration.TraitAlgorithm;
import org.shovelgame.game.domain.model.Trait;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")
public class TestDomainModel {

	@RequestMapping("domain")
	public String hero() {
		Trait trait = new Trait();
		trait.setAlg(TraitAlgorithm.DECREASE);
		trait.setIconName("supr");
		trait.persist();
		Hero hero = new Hero();
		Player p = hero.getOwner();
		return "index";
	}

}
