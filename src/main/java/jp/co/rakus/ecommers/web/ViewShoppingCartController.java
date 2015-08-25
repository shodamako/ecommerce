package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.OrderItem;

@Controller
@Transactional
@RequestMapping("/cart")
public class ViewShoppingCartController {
	
	@Autowired
	ViewShoppingCartService viewShoppingService;
	
	@RequestMapping
	public String showCart(@ModelAttribute("orderList")ArrayList<OrderItem> orderItemlist, Model model){
		
		ViewShoppingCartPage page = viewShoppingService.showCart(orderItemlist);
		model.addAttribute("page", page);
		
		return "/viewShoppingCart";
	}
}
