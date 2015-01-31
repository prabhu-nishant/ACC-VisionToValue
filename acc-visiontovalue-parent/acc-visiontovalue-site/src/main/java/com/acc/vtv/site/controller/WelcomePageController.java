package com.acc.vtv.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomePageController {

	@RequestMapping(method=RequestMethod.GET)
	public String loadWelcomePage(HttpServletRequest request,Model model){
		
				
		return "welcome";
	}
	
}
