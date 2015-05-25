package org.shovelgame.web.spring.controller.web;

import java.util.List;
import java.util.Set;

import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;
import org.shovelgame.game.domain.model.reward.ItemReward;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")
@Logger
public class TestDomainModel {

	@RequestMapping("domain")
	public String test() {
		try {
			List<Mission> s = Mission.findAllMissions();
			Mission m = s.get(0);
			Set<MissionReward> rew = m.getMissionRewards();
			for(MissionReward r: rew) {
				System.out.println(r);
			}
//			r.getItem()
			log.info(s.toString());
		} catch (Exception e) {
			log.error("", e);
		}
		return "index";
	}

}
