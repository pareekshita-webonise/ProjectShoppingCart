package com.shopperszone.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopperszone.cacheservice.CacheService;
import com.shopperszone.model.Item;
import com.shopperszone.model.User;
import com.shopperszone.service.ItemService;
import com.shopperszone.service.OrderService;
import com.shopperszone.service.UserService;
import com.shopperszone.utility.CreatePDF;

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

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView getHomePage() {
		return new ModelAndView("home", "categories", itemService.getAllCategories());
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String displayItems(Model model) {
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getAllItems());
		return "items";
	}

	@RequestMapping(value = "/items/{category}", method = RequestMethod.GET)
	public String displayCategories(Locale locale, Model model, @PathVariable("category") String category) {
		model.addAttribute("categories", itemService.getAllCategories());
		model.addAttribute("items", itemService.getCategorisedItems(category));
		return "items";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView displayCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Item> cartItems = (List<Item>) session.getAttribute("myCart");
		if (cartItems == null) {
			cartItems = new ArrayList<Item>();
		}
		return new ModelAndView("cart", "items", cartItems);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView displayOrders(RedirectAttributes redirect) {
		User user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ModelAndView("orders", "orders", orderService.getMyOrders(user));
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String purchaseItems(Model model) {
		model.addAttribute("order", null);
		return "order";
	}
	
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();

	    String fileName = "JavaHonk.pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);

	    try {

	        CreatePDF.createPDF(temperotyFilePath+"\\"+fileName);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = orderService.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}

	@RequestMapping(value = "/flushcache", method = RequestMethod.GET)
	public String flushTheCache(HttpServletRequest request) {
		cacheService.flushCache();
		return "redirect:" + request.getHeader("Referer");
	}	
}
