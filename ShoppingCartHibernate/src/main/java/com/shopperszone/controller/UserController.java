package com.shopperszone.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login")
	public String userLogin(){
		return "login";
	}	
	@RequestMapping(value = "/loginValidate")
	public String userLoginValidate(Locale locale, Model model, @RequestParam("username") String username, @RequestParam("password") String password)
	{		
		System.out.println(username+" "+password);
		boolean loggedIn = userService.validateUser(username, password);
		if(loggedIn)
		{
			User user = userService.getCurrentUser();
			model.addAttribute("user", user);
			return "redirect:home";
		}
		else
		return "redirect:login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(Locale locale, Model model){
		User user = userService.getCurrentUser();
		user=null;
		userService.setCurrentUser(user);
		model.addAttribute("user", user);
		return "redirect:home";
	}
	
	@RequestMapping(value = "/signup")
	public String userSignup(){
		return "signup";
	}	
	@RequestMapping(value="/signupValidate")
	public String userSignupSubmisson(Locale locale, Model model,@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("fname")String fname,@RequestParam("lname")String lname,@RequestParam("mobile")int mobile)
	{
		boolean signupSuccess = userService.signupSubmit(username, password, fname, lname, mobile);
		if(signupSuccess)
		{
			User user = userService.getCurrentUser();
			model.addAttribute("user", user);
			return "redirect:home";
		}
		else
		return "redirect:signup";
		
	}
}
