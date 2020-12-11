package com.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/app")
public class IndexController {
	
	@RequestMapping(path = { "/", "","/index"} )
	public String index(Model model) {
	
		return "index";
	}
	
	@RequestMapping(path = { "/login"} )
	public String login(Model model) {
		model.addAttribute("titulo","Login");
		return "login";
	}
	
	@PostMapping(path = {"/access"})
	public String login(@RequestParam String user,
			@RequestParam String pass,
			Model model) {
		
		// VAlidacion
		if(user.equals("user") && pass.equals("pass")) {
			model.addAttribute("titulo","Login");
			return "Administrador/admin";
		} else {
			model.addAttribute("titulo","Login");
			return "login";
		}
	}

}
