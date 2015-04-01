package org.shovelgame.web.spring.controller.web;

import org.shovelgame.game.domain.enumaration.TraitAlgorithm;
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
		return "index";
	}

}
