package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.ShowItemService;

/**
 * 商品詳細画面関連コントローラー
 * @author ueno
 *
 */
@Controller
@RequestMapping(value= "/showItem")
public class ShowItemController {
	@Autowired
	private ShowItemService showItemService;
	
	/**
	 * フォームの初期化.
	 * @return
	 */
	@ModelAttribute
	private AddCartForm setUpForm(){
		return new AddCartForm();
	}
	
	@RequestMapping
	public String list(){
		return "itemDetail";
	}
	/**
	 * id値の商品情報を取得.
	 * @param id id値
	 * @param model モデル
	 * @return 検索された商品情報
	 */
	@RequestMapping(value = "/findById/{itemId}")
	public String findById(@PathVariable("itemId") Long id, Model model){
		ShowItemPage item = showItemService.execute(id);
		if (item == null) {
			System.out.println(1);
			String error = "商品情報が見つかりませんでした";
			model.addAttribute("errorMessage",error);
			return list();
		}
		model.addAttribute("item", item);
		return "itemDetail";
	}
}
