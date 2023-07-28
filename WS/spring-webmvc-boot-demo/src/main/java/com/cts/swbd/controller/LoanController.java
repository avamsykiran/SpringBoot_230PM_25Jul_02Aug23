package com.cts.swbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.swbd.model.Loan;
import com.cts.swbd.service.LoanService;

@Controller
@RequestMapping("/loans") 
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ModelAndView loanPageAction() {
		return new ModelAndView("loan-page","loan", new Loan());
	}
	
	@PostMapping
	public ModelAndView computeSimpleInterest(@ModelAttribute("loan") Loan loan) {
		double interest = loanService.getSimpleInterest(loan);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loan-page");
		mv.addObject("loan",loan);
		mv.addObject("si",interest);
		
		return mv;
	}
}
