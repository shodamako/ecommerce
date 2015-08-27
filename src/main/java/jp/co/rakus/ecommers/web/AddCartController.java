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
	
	/**
	 * ショッピングカートへ商品を追加するメソッド.
	 * @param form リクエスト
	 * @param model モデル
	 * @param orderItemList セッションスコープに入れてあるショッピングカート内の商品一覧
	 * @return 商品一覧ページ
	 */
	
	@RequestMapping()
<<<<<<< HEAD
	public String addCart(@Validated AddCartForm form,BindingResult result, Model model, @ModelAttribute("orderItemList") ArrayList<OrderItem> cartItemList){
		System.out.println("varidation前");
		if(result.hasErrors()){
			return "forward:/serchItem";
		}
		System.out.println("varidation後");
		ArrayList<OrderItem> orderItemList = addCartService.addCart(form, cartItemList);
		model.addAttribute("orderItemlist", orderItemList);
=======
	public String addCart(@Validated AddCartForm form,BindingResult result, Model model, @ModelAttribute("orderItemlist") ArrayList<OrderItem> cartItemList){
		if(result.hasErrors()){
			return "forward:/serchItem";
		}
		addCartService.addCart(model, form, cartItemList);
		model.addAttribute("orderItemlist", cartItemList);
>>>>>>> 063954f290f52b3546bb0da809ccd30d417d9889
		return "forward:/serchItem/";
	}
}
