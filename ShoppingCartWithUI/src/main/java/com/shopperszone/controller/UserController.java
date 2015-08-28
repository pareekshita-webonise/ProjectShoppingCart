package com.shopperszone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@ModelAttribute("currentUser")
	public User getCurrentUser()
	{
		return userService.getUserDao().getCurrentUser();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Model model) {
		logger.info("Redirect to login");
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("currentUser", userService.getUserDao().getCurrentUser());
		return "login";
	}

	@RequestMapping(value = "/loginValidate", method = RequestMethod.POST)
	public String validateUser(@ModelAttribute("user") User user, Model model, RedirectAttributes attribute) {
		logger.info("User parameters" + user.toString());
		String msg = "";
		int status = userService.validateUser(user);
		logger.info("Login status = " + status);
		switch (status) {
			case 0:
				attribute.addFlashAttribute("user", userService.getUserDao().getCurrentUser());
				return "redirect:/";
			case 1:
				msg = "Invalid username";
				attribute.addFlashAttribute("msg", msg);
				return "redirect:/login";

			case 2:
				msg = "Invalid password";
				attribute.addFlashAttribute("msg", msg);
				return "redirect:/login";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, RedirectAttributes attribute) {
		userService.getUserDao().setCurrentUser(null);
		logger.info("Current user : " + userService.getUserDao().getCurrentUser());
		attribute.addFlashAttribute("user", userService.getUserDao().getCurrentUser());
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignup(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("currentUser", userService.getUserDao().getCurrentUser());
		return "signup";
	}

	@RequestMapping(value = "/signupValidate", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model, RedirectAttributes attribute) {
		logger.info("User parameters" + user.toString());
		String msg = "";
		if (user.getUserName().equals("") || user.getPassword().equals("") || user.getFirstName().equals("")
				|| user.getLastName().equals("")) {
			msg = "Please fill the details";
			attribute.addFlashAttribute("msg", msg);
			return "redirect:/signup";
		} else {
			userService.addUser(user);
			msg = "Registered successfully";
			attribute.addFlashAttribute("msg", msg);
			attribute.addFlashAttribute("user", userService.getUserDao().getCurrentUser());
			logger.info("Current user : " + userService.getUserDao().getCurrentUser());
			return "redirect:/";
		}
	}
}
