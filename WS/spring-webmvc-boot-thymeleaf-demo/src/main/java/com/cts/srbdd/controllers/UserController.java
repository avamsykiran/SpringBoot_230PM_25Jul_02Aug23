package com.cts.srbdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.srbdd.entities.User;
import com.cts.srbdd.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository;

    
    @GetMapping("/index")
    public ModelAndView showUserList() {
        return new ModelAndView("index","users", userRepository.findAll());
    }
    
    @GetMapping("/signup")
    public ModelAndView showSignUpForm() {
        return new ModelAndView("add-user","user",new User());
    }
    
    @PostMapping("/adduser")
    public ModelAndView addUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("add-user","user",user);
        }
        
        userRepository.save(user);
        return new ModelAndView( "redirect:/index");
    }
    
    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElse(null);
        return new ModelAndView("update-user","user",user);
    }
    
    @PostMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") long id,@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return new ModelAndView("update-user","user",user);
        }
        
        userRepository.save(user);

        return new ModelAndView( "redirect:/index");
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        
        return "redirect:/index";
    }
}
