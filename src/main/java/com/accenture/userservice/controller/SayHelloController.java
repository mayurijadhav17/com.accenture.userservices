package com.accenture.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class SayHelloController {
		

	@RequestMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "Hello There , Happy Learning !!";
	}
	
	

}
