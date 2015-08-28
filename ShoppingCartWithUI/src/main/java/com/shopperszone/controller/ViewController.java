package com.shopperszone.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;
	
	@ModelAttribute("currentUser")
	public User getCurrentUser()
	{
		return userService.getUserDao().getCurrentUser();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayRoot(Locale locale, Model model, RedirectAttributes attribute) {
		User currentUser = userService.getUserDao().getCurrentUser();
		if(currentUser!=null)
		logger.info("Current user : "+currentUser.toString());
		attribute.addFlashAttribute("user", currentUser);
		return "home";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String displayItems(Locale locale, Model model) {
		List<Item> items = itemService.getAllItems();
		model.addAttribute("items", items);
		return "items";
	}

	@RequestMapping(value = "/items/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		List<Item> items = itemService.getCategorisedItems(category);
		model.addAttribute("items", items);
		return "items";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String displayCart(Locale locale, Model model) {
		List<Item> cartItems = itemService.getCartItems();
		model.addAttribute("items", cartItems);
		return "cart";
	}
	
	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
	public String displayOrders(Locale locale, Model model, RedirectAttributes redirect) {
		User user = userService.getUserDao().getCurrentUser();
		if(user == null)
		{
			redirect.addFlashAttribute("user", new User());
			return "redirect:/login";
		}
		List<Order> myorders = orderService.getMyOrders(user);
		model.addAttribute("orders", myorders);
		System.out.println("Total Orders : "+myorders.size());
		return "myorders";
	}
}
