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

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class CartController {

	//private static final Logger LOG = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItem(Model model, HttpServletRequest request, @RequestParam int[] items) {
		HttpSession session = request.getSession();
		
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		itemService.addToCart(cartItems, items);
		session.setAttribute("myCart", cartItems);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String purchaseItems(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		Order order = orderService.placeOrder(user, cartItems);
		session.setAttribute("myCart", null);
		model.addAttribute("order", order);
		//return "redirect:/order";
		return "order";
	}
}
