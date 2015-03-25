package org.shovelgame.web.spring.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.shovelgame.annotation.Logger;
import org.shovelgame.domain.OtherUser;
import org.shovelgame.domain.TxUser;
import org.shovelgame.web.spring.controller.web.common.RejectErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/register/")
@Logger
public class RegistrationController extends RejectErrorController {

	@ModelAttribute("user")
	public TxUser createNewUser() {		
		return new OtherUser();
	}

	@RequestMapping
	private String showForm() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	private String save(@ModelAttribute("user") @Valid OtherUser user,
			Errors errors, HttpServletRequest request) {
		check: if (!errors.hasErrors()) {
			try {
				user.hashPassword();
				user.persist();
				user.flush();
			} catch (Exception e) {
				log.error("", e);
				break check;
			}
			return "redirect:/";
		}
		user.setPassword(null);
		user.setMatchPassword(null);
		rejectFormErrors(RequestContextUtils.getLocale(request), errors);
		return "register";
	}
}
