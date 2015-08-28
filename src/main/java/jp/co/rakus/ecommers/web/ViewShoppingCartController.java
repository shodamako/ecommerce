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
@SessionAttributes("orderItemlist")
public class ViewShoppingCartController {
	
	@Autowired
	ViewShoppingCartService viewShoppingService;
	
	@ModelAttribute
	public AddCartForm setUpAddCartForm(){
		return new AddCartForm();
	}
	
	/**
	 * カート内商品の一覧を出すメソッド.
	 * @param orderItemlist カート内商品一覧のリスト
	 * @param model
	 * @return　カート内商品一覧のリスト、無い場合は空のリストとメッセージを返す
	 */
	@RequestMapping
	public String showCart(@ModelAttribute("orderItemlist")ArrayList<OrderItem> orderItemlist, Model model){
		
		ViewShoppingCartPage page = viewShoppingService.showCart(orderItemlist, model);		
		model.addAttribute("page", page);
		
			if(orderItemlist.size() == 0){
				String message = "カートに商品がありません";
				model.addAttribute("message", message);
			}		
		
		return "/viewShoppingCart";
	}
}
