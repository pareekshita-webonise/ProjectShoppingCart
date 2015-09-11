package com.shopperszone.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/admin/users", method=RequestMethod.GET)
	public String displayUsers(Model model){
		model.addAttribute("users",userService.getAllUsers());
		return "users";
	}
	
	@RequestMapping(value="/admin/additem", method=RequestMethod.GET)
	public String displayAddItem(Model model){
		model.addAttribute("item",new Item());
		return "addItem";
	}
	
	@RequestMapping(value="/admin/updateitem/{id}", method=RequestMethod.GET)
	public String displayUpdateItem(Locale locale,Model model, @PathVariable("id") int id){
		model.addAttribute("item",itemService.getItemById(id));
		return "updateItem";
	}
	
	@RequestMapping(value="/admin/orders", method=RequestMethod.GET)
	public String displayOrders(Model model){
		model.addAttribute("orders", orderService.getAllOrders());
		return "orders";
	}
	@RequestMapping(value="/admin/inventory", method=RequestMethod.GET)
	public String displayItems(Model model){
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getAllItems());
		return "inventory";
	}
	@RequestMapping(value = "/admin/inventory/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getCategorisedItems(category));
		return "inventory";
	}
	@RequestMapping(value="/admin/addinventory", method=RequestMethod.POST)
	public String addItemInventory(@ModelAttribute("item") Item item,Model model){
		itemService.addItem(item);
		model.addAttribute("message", "Item Added to Inventory");
		return "addItem";
	}
	@RequestMapping(value="/admin/updateinventory", method=RequestMethod.POST)
	public String updateItemInventory(@ModelAttribute("item") Item item,Model model){
		itemService.updateItem(item);
		model.addAttribute("message", "Item Updated to Inventory");
		return "updateItem";
	}
}
