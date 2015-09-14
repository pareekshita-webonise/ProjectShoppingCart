package com.shopperszone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.cacheservice.CacheService;
import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;
import com.shopperszone.model.User;
import com.shopperszone.model.UserRole;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@SuppressWarnings("unchecked")
@Controller
public class ViewController {

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheService cacheService;
	
	@ModelAttribute("isAdmin")
	public boolean isAdminActive()
	{
		System.out.println("isAdmin called");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(userName.equals("anonymousUser"))
			return false;		
		User user;
		try {
			user = userService.getUserByName(userName);
			Set<UserRole> userRoles = user.getUserRole();
			for(UserRole userRole : userRoles)
			{
				System.out.println(userRole.getRole());
				if(userRole.getRole().equals("ROLE_ADMIN"))
					return true;
			}
		} catch (ShoppersZoneException e) {}		
		return false;
	}

	@RequestMapping(value = { "/", "/home" })
	public String getHomePage(Model model) {

		try {
			model.addAttribute("categories", itemService.getAllCategories());
		} catch (ShoppersZoneException e) {
			model.addAttribute("message","Not able to fetch categories");
			model.addAttribute("categories", new ArrayList<String>(0));			
		}
		return "home";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String displayItems(Model model) {
		try {
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getAllItems());
		}
		catch (ShoppersZoneException e) {
			model.addAttribute("items", null);
			e.printStackTrace();
		}
		return "items";
	}

	@RequestMapping(value = "/items/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		try {
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getCategorisedItems(category));
		}
		catch (ShoppersZoneException e) {
			model.addAttribute("items", null);
			e.printStackTrace();
		}
		return "items";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView displayCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		return new ModelAndView("cart", "items", cartItems);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String displayOrders(RedirectAttributes redirect,Model model) {
		try {
			User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());		
			model.addAttribute("orders", orderService.getMyOrders(user));
			return "orders";
		} catch (ShoppersZoneException e) {
			
			redirect.addFlashAttribute("message", "Error fetching past orders.. Please try again later!!");
			e.printStackTrace();
			
		}
		return "redirect:/account";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String purchaseItems(Model model) {
		model.addAttribute("order", null);
		return "order";
	}	

	@RequestMapping(value = "/flushcache", method = RequestMethod.GET)
	public String flushTheCache(HttpServletRequest request) {
		cacheService.flushCache();
		return "redirect:" + request.getHeader("Referer");
	}	
}
