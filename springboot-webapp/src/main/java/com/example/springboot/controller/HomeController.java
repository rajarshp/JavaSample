package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
//@RequestMapping("/app")
@Controller
public class HomeController
{

	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
}
