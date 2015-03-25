package org.shovelgame.web.spring.controller.web;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/audio/")
public class AudioStreamController {

	@RequestMapping(value = "/{fileName.suffix}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("fileName.suffix") String fileName) {
	    return new FileSystemResource("c://share//"+fileName); 
	}
	
}
