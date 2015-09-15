package com.shopperszone.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Controller
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("authManager")
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailsService userDetailsSvc;

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

	@RequestMapping(value = "/account**", method = RequestMethod.GET)
	public ModelAndView getUserProfile(Model model) {
		User user = null;
		try {
			user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		} catch (ShoppersZoneException e) {
			e.printStackTrace();
		}
		return new ModelAndView("account", "user", user);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView showSignup(Model model) {
		return new ModelAndView("signup", "user", new User());
	}
	
	@RequestMapping(value = "/check", method=RequestMethod.POST)
	public @ResponseBody String home1(Model model, @RequestParam(value = "id", required = false) String id) throws ShoppersZoneException {
		return (userService.isAlreadyRegistered(id)?"FAIL":"SUCCESS");
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		/*boolean isRegistered=false;
		try
		{
			isRegistered = userService.isAlreadyRegistered(user);
		}
		catch(ShoppersZoneException e){
			
		}
		
		if(isRegistered)
		{
			model.addAttribute("message", "Username already exist.");
			return "signup";
		}*/
		
		try {
			userService.addUser(user);
			UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUsername());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
					user.getPassword(), userDetails.getAuthorities());
			authManager.authenticate(auth);

			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				return "redirect:/";
			}
		} catch (Exception e) {
			LOG.debug("Problem authenticating user" + user.getUsername(), e);
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, HttpServletRequest request,
			RedirectAttributes attribute) {
		try {
			userService.updateUser(user);
			attribute.addFlashAttribute("message", "Updated successfully");
		} catch (ShoppersZoneException e) {
			attribute.addFlashAttribute("message", "Error updating user details");
			e.printStackTrace();
		}
		
		return "redirect:" + request.getHeader("referer");
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
