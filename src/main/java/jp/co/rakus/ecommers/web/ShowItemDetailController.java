package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.service.ShowItemDetailService;

/**
 * 商品詳細画面を表示する操作を行うコントローラ.
 * 
 * @author sakikoyama
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/Admin/ShowItemDetail")
public class ShowItemDetailController {
	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 編集ボタンフォーム初期化.
	 * @return 編集ボタン
	 */
	@ModelAttribute
	public ShowItemDetailForm setUpDetailForm(){
		return new ShowItemDetailForm();
	}
	
	/**
	 * 指定した商品の詳細情報を表示.
	 * 
	 * @param showForm
	 *            フォーム
	 * @param model
	 *            モデル
	 * @return 商品情報詳細ページ
	 */
	@RequestMapping
	public String showItemDetail(ShowItemDetailForm showItemDetailForm, HttpSession session,RedirectAttributes redirectAttributes,Model model) {
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		model.addAttribute("editItemForm", showItemDetailService.execute(showItemDetailForm));
		return "/AdminItem/editItem";
	}
}
