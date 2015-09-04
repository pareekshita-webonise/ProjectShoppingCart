package com.shopperszone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Controller
public class UserController {

	//private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	String msg = "";

	@ModelAttribute("msg")
	public String getMsg() {
		return msg;
	}

	@RequestMapping("/login")
	public ModelAndView getLoginForm(@ModelAttribute User user,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		String message = "";
		if (error != null) {
			message = "Incorrect username or password !";
		} else if (logout != null) {
			message = "Logout successful !";
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping("/admin**")
	public String getAdminProfile() {
		return "admin";
	}

	@RequestMapping(value="/account**", method = RequestMethod.GET)
	public String getUserProfile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.getUserByName(username);
		model.addAttribute("user", user);
		return "account";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, Model model, RedirectAttributes attribute) {
		userService.addUser(user);
		msg = "Registered successfully";
		attribute.addFlashAttribute("message", msg);
		msg="";
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, HttpServletRequest request, RedirectAttributes attribute) {
		userService.updateUser(user);
		String referer = request.getHeader("referer");
		return "redirect:"+referer;
	}

	@RequestMapping("/403")
	public ModelAndView getAccessDenied() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (auth != null)
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				username = userDetail.getUsername();
			}

		return new ModelAndView("403", "username", username);
	}
}
