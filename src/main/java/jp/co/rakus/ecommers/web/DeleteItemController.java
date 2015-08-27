package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.service.DeleteItemService;

/**
 * 商品を削除するコントローラ.
 * @author sakikoyama
 *
 */
@Controller
@Transactional
@RequestMapping(value="/Admin/DeleteItem",method=RequestMethod.POST)
public class DeleteItemController {
	@Autowired
	private DeleteItemService deleteItemService;
	
	/**
	 * 編集ボタンフォーム初期化.
	 * @return 編集ボタン
	 */
	@ModelAttribute
	public ShowItemDetailForm setUpDetailForm(){
		return new ShowItemDetailForm();
	}
	
	/**
	 * 商品削除フォーム初期化.
	 * @return
	 */
	@ModelAttribute
	public EditItemForm setUpEditItemForm(){
		return new EditItemForm();
	}
	
	/**
	 * 商品を削除.
	 * @param deletForm
	 * @param model
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String deleteItem(DeleteItemForm deleteItemForm, HttpSession session,RedirectAttributes redirectAttributes){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		
		deleteItemService.execute(deleteItemForm);
		return "redirect:/Admin/Item";
	}
}

