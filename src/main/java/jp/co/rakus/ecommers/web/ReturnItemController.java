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
import jp.co.rakus.ecommers.service.ReturnItemService;

@Controller
@Transactional
@RequestMapping(value="/Admin/ReturnItem",method=RequestMethod.POST)
public class ReturnItemController {
	@Autowired
	private ReturnItemService returnItemService;
	
	/**
	 * 編集ボタンフォーム初期化.
	 * @return 編集ボタン
	 */
	@ModelAttribute
	public ShowItemDetailForm setUpDetailForm(){
		return new ShowItemDetailForm();
	}
	
	/**
	 * 商品再表示フォーム初期化.
	 * @return
	 */
	@ModelAttribute
	public ReturnItemForm setUpEditItemForm(){
		return new ReturnItemForm();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String ReturnItem(ReturnItemForm returnItemForm,HttpSession session,RedirectAttributes redirectAttributes){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		returnItemService.execute(returnItemForm);
		return "redirect:/Admin/Item";
	}
}
