package org.shovelgame.atlant.web.spring.controller.ws;

import org.shovelgame.annotation.Logger;
import org.shovelgame.gcm.GCMService;
import org.shovelgame.gcm.GCMServiceException;
import org.shovelgame.gcm.body.GCMBody;
import org.shovelgame.gcm.body.GCMSimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gcm/")
@Logger
public class GCMController {
	
	@Autowired
	private GCMService service; 
	
	@RequestMapping(value="pair")
	public void pair(@RequestParam String registrationId) { //NOSONAR
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String name = authentication.getName();
		this.service.pair(1L, registrationId);
	}
	
	@RequestMapping(value="send")
	public void send(@RequestParam String message, @RequestParam Long driverId) {
		GCMBody body = new GCMBody();
		body.setRegistrationIds(this.service.getRegistrationIds(driverId));
		GCMSimpleMessage m = new GCMSimpleMessage();
		m.setMessage(message);
		body.setData(m);
		try {
			this.service.send(body);
		} catch (GCMServiceException e) {
			log.error("", e);
		}
	}
}
