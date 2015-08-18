package com.shopperszone.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.shopperszone.model.Inventory;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class CartController {
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(CartController.class);
	 */

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(Locale locale, Model model) {		
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String displayHome(Locale locale, Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String displayItems(Locale locale, Model model) {
		
		Inventory allDbItems = itemService.getAllDbItems();
		model.addAttribute("map", allDbItems.getItems());
		return "items";
	}
	
	@RequestMapping(value = "/items/{category}", method = RequestMethod.GET)
	public String displayMobiles(Locale locale, Model model, @PathVariable("category") String category) {
		System.out.println(category);
		Inventory categoryItems = itemService.getCategorisedItems(category.toLowerCase());
		model.addAttribute("map", categoryItems.getItems());
		return "items";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItems(Locale locale, Model model, @RequestParam int[] items) {
		Inventory myCart = itemService.addItemsToCart(items);
		model.addAttribute("map", myCart.getItems());
		return "add";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyItems(Locale locale, Model model) 
	{
		Inventory myCart = itemService.getCartItems();
		if(myCart==null)
		{
			System.out.println("error. no items in cart");
			return "items";
		}
		User user = userService.getCurrentUser();
		if(user==null)
		{
			System.out.println("error. please log in");
			return "login";
		}
		boolean res=orderService.placeOrder(user, myCart);
		if(res)
		{
			System.out.println(myCart);
			model.addAttribute("cart", myCart.getItems());
			model.addAttribute("message", "Items successfully purchased");
			return "buy";
		}
		else
		{
			return "add";
		}
	}
}
