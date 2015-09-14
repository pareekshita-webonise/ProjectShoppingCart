package com.shopperszone.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String displayUsers(Model model, RedirectAttributes attributes) {
		try {
			model.addAttribute("users", userService.getAllUsers());
			return "users";
		} catch (ShoppersZoneException e) {
			attributes.addFlashAttribute("message", "Error Fetching all users.. Please try again later!!");
		}
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/additem", method = RequestMethod.GET)
	public String displayAddItem(Model model) {
		model.addAttribute("item", new Item());
		return "addItem";
	}

	@RequestMapping(value = "/admin/updateitem/{id}", method = RequestMethod.GET)
	public String displayUpdateItem(Locale locale, Model model, @PathVariable("id") int id) {
		try {
			model.addAttribute("item", itemService.getItemById(id));
		} catch (ShoppersZoneException e) {
			model.addAttribute("item", null);
			e.printStackTrace();
		}
		return "updateItem";
	}

	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public String displayOrders(Model model, RedirectAttributes redirect) {
		try {
			model.addAttribute("orders", orderService.getAllOrders());
		} catch (ShoppersZoneException e) {
			redirect.addFlashAttribute("message", "Error fetching all orders.. Please try again later!!");
			e.printStackTrace();
		}
		return "orders";
	}

	@RequestMapping(value = "/admin/inventory", method = RequestMethod.GET)
	public String displayItems(Model model) {
		try {
			model.addAttribute("categories", itemService.getAllCategories());
			model.addAttribute("items", itemService.getAllItems());
		} catch (ShoppersZoneException e) {
			model.addAttribute("items", null);
		}
		return "inventory";
	}

	@RequestMapping(value = "/admin/inventory/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		try {
			model.addAttribute("categories", itemService.getAllCategories());
			model.addAttribute("items", itemService.getCategorisedItems(category));
		} catch (ShoppersZoneException e) {
			model.addAttribute("items", null);
			e.printStackTrace();
		}

		return "inventory";
	}

	@RequestMapping(value = "/admin/addinventory", method = RequestMethod.POST)
	public String addItemInventory(@ModelAttribute("item") Item item, Model model) {
		try {
			itemService.addItem(item);
			model.addAttribute("message", "Item Added to Inventory");
		} catch (ShoppersZoneException e) {
			model.addAttribute("message", "Error adding item to the inventory");
			e.printStackTrace();
		}
		return "addItem";
	}

	@RequestMapping(value = "/admin/updateinventory", method = RequestMethod.POST)
	public String updateItemInventory(@ModelAttribute("item") Item item, Model model) {
		try {
			itemService.updateItem(item);
			model.addAttribute("message", "Item Updated to Inventory");
		} catch (ShoppersZoneException e) {
			model.addAttribute("message", "Error updating the Item to Inventory");
			e.printStackTrace();
		}
		return "updateItem";
	}
}
