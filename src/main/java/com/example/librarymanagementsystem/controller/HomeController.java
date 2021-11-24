package com.example.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		return "redirect:/users/";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}
