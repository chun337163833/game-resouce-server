package org.shovelgame.web.spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class LoginController {
 
	@RequestMapping(value="/login-form", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
 
	}
	
	@RequestMapping(value="/login-failed", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@ModelAttribute("_loginPage")
	public boolean isLoginPage() {
		return true;
	}
	
}
