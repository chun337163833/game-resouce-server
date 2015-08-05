package org.shovelgame.web.spring.controller.web;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.model.Mission;
import org.shovelgame.game.domain.model.MissionReward;
import org.shovelgame.game.domain.model.reward.ItemReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shovelgame.async.TestAsync;

@Controller
@RequestMapping("/test/")
@Logger
public class TestDomainModel {
	
	@Autowired
	private TestAsync as;
	
	@RequestMapping("async")
	public String testAsync() {
		as.doIt();
		return "index";
	}
	
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
