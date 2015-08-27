package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.page.LoginAdminPage;
import jp.co.rakus.ecommers.service.LoginAdminService;

/**
 * 管理者のログイン関連のコントローラ.
 * @author rksuser
 *
 */
@Controller
@RequestMapping("/Admin")
@SessionAttributes("page")
public class LoginAdminController {

	@Autowired
	private LoginAdminService service;
	
	/**
	 * フォームの初期化.
	 * @return 初期化のフォーム
	 */
	@ModelAttribute
	public LoginAdminForm setUpform(){
		return new LoginAdminForm();
	}
	
	/**
	 * ログイン画面に遷移.
	 * @return
	 */
	@RequestMapping()
	public String index(){
		return "./Admin/administerLogin";
	}
	
	/**
	 * ログイン処理.
	 * @param form 入力データ
	 * @param result エラーデータ
	 * @param model スコープ
	 * @return リダイレクト後、メニュー画面に遷移
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginAdminForm form,BindingResult result,Model model){
		if (result.hasErrors()) {
			return index();
		}
		LoginAdminPage page = service.execute(form);
		
		if(page==null){
			ObjectError error = new ObjectError("checkerror", "入力情報が間違っています");
			result.addError(error);
			return index();
		}
		model.addAttribute("page", page);
		return "redirect:/Admin/showMenu";
	}
}
