package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/param")
	public String displayParam(@RequestParam String name, ModelMap model)
	{
		model.put("name", name);
		return "home";
	}
}
