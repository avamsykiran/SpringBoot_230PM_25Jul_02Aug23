package com.cts.swbdd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/"})
public class DefaultController {

	@GetMapping
	public String showHomeAction() {
		return "index";
	}
	
	@RequestMapping("/header")
	public String getHeaderFragmentAction() {
		return "header-fragement";
	}
}
