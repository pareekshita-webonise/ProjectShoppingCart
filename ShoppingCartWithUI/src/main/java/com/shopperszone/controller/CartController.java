package com.shopperszone.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItems(Locale locale, Model model, @RequestParam int[] items, HttpServletRequest request) {
		itemService.addToCart(items);
		List<Item> cartItems = itemService.getCartItems();
		String referer = request.getHeader("Referer");
		logger.info("Items added : " + cartItems.size());
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyItems(Model model) {
		List<Item> cartItems = itemService.getCartItems();
		User user = userService.getUserDao().getCurrentUser();
		if (user == null) {
			logger.info("User is null");
			return "redirect:/login";
		} else {
			Order order = orderService.placeOrder(user, cartItems);
			logger.info(order.toString());
			model.addAttribute("order", order);
			itemService.setCartItems(null);
			return "order";
		}
	}
}
