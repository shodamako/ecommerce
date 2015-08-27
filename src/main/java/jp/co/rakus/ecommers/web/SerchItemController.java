package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.SerchItemService;

/**
 * アイテム一覧表示を行うコントローラー.
 * @author takayuki.honma
 *
 */
@Controller
@RequestMapping("/serchItem")
@Transactional
public class SerchItemController {
	
	@Autowired
	private SerchItemService serchItemService;
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public SerchItemForm setUpForm() {
		return new SerchItemForm();
	}
	
	/**
	 * @param form　検索フォーム
	 * @param model　モデル
	 * @return　
	 */
	@RequestMapping(value = "/")
	public String list(SerchItemForm form, Model model) {
		SerchItemPage serchItempage = serchItemService.execute(form);
		model.addAttribute("serchItempage", serchItempage);
		Boolean zero = serchItemService.isNotExsitKeyWord(form);
		model.addAttribute("zero", zero);
		
		return "/itemList";
	}

}
