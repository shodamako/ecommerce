package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping
	public String list(){
		return "/item/itemDetail";
	}
	
	/**
	 * id値の商品情報を取得.
	 * @param id id値
	 * @param model モデル
	 * @return 検索された商品情報
	 */
	@RequestMapping(value = "/findbyId")
	public String findById(Long id, Model model){
		ShowItemPage item = showItemService.execute(id);
		model.addAttribute("item", item);
		return "itemDetail";
	}
}
