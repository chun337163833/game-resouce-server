package org.shovelgame.web.spring.controller.web;

import java.util.List;

import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")
@Logger
public class TestDomainModel {

	@RequestMapping("domain")
	public String hero() {
		try {
			List<Skill> s = Skill.findAllSkills();
			log.info(s.toString());
		} catch (Exception e) {
			log.error("", e);
		}
		return "index";
	}

}
