package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.service.AddCartService;


/**
 * ショッピングカートへ商品を追加するControllerクラス.
 * @author rksuser
 *
 */
@Controller
@Transactional
@RequestMapping("/addCart")
@SessionAttributes("orderItemlist")
public class AddCartController {

	@Autowired
	private AddCartService addCartService;
	
	@Autowired
	private ShowItemController showItemController;
	
	/**
	 * ショッピングカートへ商品を追加するメソッド.
	 * @param form リクエスト
	 * @param model モデル
	 * @param orderItemList セッションスコープに入れてあるショッピングカート内の商品一覧
	 * @return 商品一覧ページ
	 */
	
	@RequestMapping
	public String addCart(@Validated AddCartForm form, BindingResult result, Model model, @ModelAttribute("orderItemlist") ArrayList<OrderItem> cartItemList){
		if(result.hasErrors()){
			return showItemController.findById(form.getItemId(), model);
		}
		int quantity = form.getQuantity();
		for(OrderItem item: cartItemList){
			if(item.getItemId() == form.getItemId())
			quantity += item.getQuantity(); 
		}
		if(quantity > 1000){
			String quantityError = "注文数の上限は1000個です";
			model.addAttribute("quantityError",quantityError);
			return "forward:/cart";
		}
		
		addCartService.addCart(model, form, cartItemList);
		model.addAttribute("orderItemlist", cartItemList);
		return "redirect:/serchItem/";
	}
	
	@RequestMapping("/update")
	public String update(@Validated AddCartForm form, BindingResult result, Model model, @ModelAttribute("orderItemlist") ArrayList<OrderItem> cartItemList){
		if(result.hasErrors()){
			return "forward:/cart";
		}
		addCartService.update(model, form, cartItemList);
		model.addAttribute("orderItemlist", cartItemList);
		return "redirect:/cart";
	}
}
