package com.shopperszone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.cacheservice.CacheService;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class ViewController {

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;

	@Autowired
	CacheService cacheService;

	@RequestMapping(value = { "/", "/home" })
	public String getHomePage(Model model) {
		List<String> categories = itemService.getAllCategories();
		model.addAttribute("categories", categories);
		return "home";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String displayItems(Locale locale, Model model) {
		List<Item> items = itemService.getAllItems();
		List<String> categories = itemService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("items", items);
		return "items";
	}

	@RequestMapping(value = "/items/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		List<Item> items = itemService.getCategorisedItems(category);
		List<String> categories = itemService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("items", items);
		return "items";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String displayCart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		model.addAttribute("items", cartItems);
		return "cart";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String displayOrders(Model model, RedirectAttributes redirect) {
		User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Order> orders = orderService.getMyOrders(user);
		model.addAttribute("orders", orders);
		return "orders";
	}

	@RequestMapping(value = "/flushcache", method = RequestMethod.GET)
	public String flushTheCache(HttpServletRequest request) {
		cacheService.flushCache();
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
