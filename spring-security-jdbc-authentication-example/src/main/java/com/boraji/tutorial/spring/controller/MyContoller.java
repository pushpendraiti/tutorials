package com.boraji.tutorial.spring.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyContoller {

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    model.addAttribute("message", "You are logged in as " + principal.getName());
    return "index";
   
  }
  @RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView visitAdmin() {
		ModelAndView model = new ModelAndView("admin");
		model.addObject("title", "Admministrator Control Panel");
		model.addObject("message", "This page demonstrates how to use Spring security.");
		
		return model;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView visitSecurity(Model m) {
		ModelAndView model = new ModelAndView("security");
		return model;
	}
	 @RequestMapping(value="/error",method = RequestMethod.GET)
	    public String error(ModelMap model)
	    {
	        model.addAttribute("error", "true");
	        return "error";

	    }
	 @RequestMapping(value="/logout",method = RequestMethod.GET)
	    public String logout(ModelMap model)
	    {
	        return "logout";
	    }
}
