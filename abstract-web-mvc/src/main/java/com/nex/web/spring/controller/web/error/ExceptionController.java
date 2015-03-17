package com.nex.web.spring.controller.web.error;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nex.annotation.Logger;

@Controller
@RequestMapping("/error/")
@ControllerAdvice
@Logger
public class ExceptionController {

//	@RequestMapping("{errorcode}")
//	public String doError(@PathVariable(value="errorcode") int errorcode, Model model) {
//		model.addAttribute("code", errorcode);
//		model.addAttribute("_contextTemplates", "");
//		model.addAttribute("_errorpage", "/exceptions/error.ftl");
//		model.addAttribute("title", "Error: " + errorcode);
//		return "decorators/main-decorator";
//	}
	
	@RequestMapping("{code}")
	public String doError(@PathVariable int code, Model model) {
		model.addAttribute("code", code);
		model.addAttribute("_contextTemplates", "");
		model.addAttribute("title", code);
		switch (code) {
		case 404:
			return "errors/404";
		default:
			return "errors/error";
		}
	}
	
	@ExceptionHandler(Exception.class)
	public void globalExceptionhandler(HttpServletResponse resp, Exception ex) {
		try {
			log.error("", ex);
			resp.sendError(500);
		} catch (IOException e) {
			log.error("", e);
		}
	}
	
//	@ExceptionHandler(NoAccessSecurityException.class)
//	public void noAccessHandler(HttpServletResponse resp) {
//		try {
//			resp.sendError(403);
//		} catch (IOException e) {
//			log.error("", e);
//		}
//	}
	
}
