package org.shovelgame.web.spring.controller.web;

import org.shovelgame.game.domain.model.HeroModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/domain/")
public class TestDomainModel {

	@RequestMapping("hero")
	public String hero() {
		HeroModel model = new HeroModel();
		model.persist();
//		new Teams().getMinion();
//		Team
		return "index";
	}

}
