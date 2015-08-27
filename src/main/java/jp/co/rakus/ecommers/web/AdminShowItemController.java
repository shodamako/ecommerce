package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.service.ShowItemService;

/**
 * ShowItemController
 * 商品一覧を表示するコントローラー.
 * @author Reo Okumura
 */
@Controller
@RequestMapping(value="/Admin/Item")
@SessionAttributes("page")
public class AdminShowItemController {
	@Autowired
	ShowItemService showItemService;
	/**
	 * 検索フォーム初期化.
	 * @return 検索フォーム
	 */
	@ModelAttribute
	public ShowItemForm setUpShowForm(){
		return new ShowItemForm();
	}
	/**
	 * 編集ボタンフォーム初期化.
	 * @return 編集ボタン
	 */
	@ModelAttribute
	public ShowItemDetailForm setUpDetailForm(){
		return new ShowItemDetailForm();
	}
	/**
	 * 商品一覧画面.
	 * @param model スコープ
	 * @return 商品一覧画面
	 */
	@RequestMapping
	public String list(ShowItemForm form,Model model,HttpSession session,RedirectAttributes redirectAttributes){
		//ログインチェック　ここから
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		//ここまで
		model.addAttribute("itemPage", showItemService.execute(form));
		return "AdminItem/itemList";
	}
}
