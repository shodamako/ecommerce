package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.service.ViewShoppingCartService;

/**
 * ショッピングカート表示のコントローラークラス.
 * @author ShoKodama
 *
 */
@Controller
@Transactional
@RequestMapping("/cart")
@SessionAttributes("orderItemList")
public class ViewShoppingCartController {
	
	@Autowired
	ViewShoppingCartService viewShoppingService;
		
	/**
	 * @param orderItemlist
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String showCart(@ModelAttribute("orderList")ArrayList<OrderItem> orderItemlist, Model model){
				
		ViewShoppingCartPage page = viewShoppingService.showCart(orderItemlist, model);		
		model.addAttribute("page", page);
		
		return "/viewShoppingCart";
	}
}
