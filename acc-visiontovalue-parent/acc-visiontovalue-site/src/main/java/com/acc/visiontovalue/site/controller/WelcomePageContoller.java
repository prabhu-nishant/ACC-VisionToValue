package com.acc.visiontovalue.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomePageContoller {

	@Value("${acc.visiontovalue.site.basepath}")
	private String basepath;

	@RequestMapping(method=RequestMethod.GET)
	public String loadWelcomePage(HttpServletRequest request,Model model){
		
		model.addAttribute("base_path",basepath);	
		return "welcome";
	}
	
}
