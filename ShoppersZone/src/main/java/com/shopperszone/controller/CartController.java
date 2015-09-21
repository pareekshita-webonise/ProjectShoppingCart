package com.shopperszone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;
import com.shopperszone.utility.SendMail;

@SuppressWarnings("unchecked")
@Controller
public class CartController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItem(RedirectAttributes attributes, HttpServletRequest request, @RequestParam int[] items) {
		HttpSession session = request.getSession();

		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		try {
			itemService.addToCart(cartItems, items);
			session.setAttribute("myCart", cartItems);
			attributes.addFlashAttribute("message", "Items added to cart");
		} catch (ShoppersZoneException e) {
			attributes.addFlashAttribute("message", "Error adding items to cart");
			e.printStackTrace();
		}
		
		return "redirect:" + request.getHeader("Referer");
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String purchaseItems(Model model, HttpServletRequest request, RedirectAttributes attributes) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");		
		try {
			User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
			Order order;
			order = orderService.placeOrder(user, cartItems);
			SendMail mail = new SendMail();
			mail.sendMail(order);
			session.setAttribute("myCart", null);
			model.addAttribute("order", order);
			return "order";
		} catch (ShoppersZoneException e) {
			attributes.addFlashAttribute("message", "Error placing the order. Please try again..!");
			e.printStackTrace();
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = "/deleteItems", method = RequestMethod.POST)
	public String deleteItems(HttpServletRequest request, @RequestParam int[] items, RedirectAttributes attributes) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		try {
			itemService.deleteItemsFromCart(cartItems, items);
			session.setAttribute("myCart", cartItems);
			attributes.addFlashAttribute("message", items.length+" Items removed from cart");
		} catch (ShoppersZoneException e) {
			attributes.addFlashAttribute("message", items.length+" Error removing items from cart");
			e.printStackTrace();
		}		
		return "redirect:" + request.getHeader("Referer");
	}
}
