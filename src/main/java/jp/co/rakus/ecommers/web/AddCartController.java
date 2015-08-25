package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
//@SessionAttributes(types={UserPage.class, ArrayList.class})
public class AddCartController {

	@Autowired
	private AddCartService addCartService;
	
	/**
	 * フォームの初期化.
	 * @return
	 */
	@ModelAttribute
	private AddCartForm setUpForm(){
		return new AddCartForm();
	}
	/**
	 * ショッピングカートへ商品を追加するメソッド.
	 * @param form リクエスト
	 * @param model モデル
	 * @param orderItemList セッションスコープに入れてあるショッピングカート内の商品一覧
	 * @return 商品一覧ページ
	 */
	@RequestMapping()
	public String addCart(AddCartForm form, Model model, @ModelAttribute("") ArrayList<OrderItem> orderItemList){
		ArrayList<OrderItem> cartItemList = addCartService.addCart(form, orderItemList);
		model.addAttribute("cartItemList", cartItemList);
		return "";
	}
	
}
