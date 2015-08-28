package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.service.LoginUserService;

/**
 * ユーザのログインをするControllerクラス
 * 
 * @author rksuser
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/loginUser")
@SessionAttributes("user")
public class LoginUserController {
	@Autowired
	private LoginUserService loginUserService;

	@ModelAttribute
	private LoginUserForm setUpForm() {
		return new LoginUserForm();
	}

	/**
	 * ログイン画面への遷移用メソッド.
	 * @return ログイン画面
	 */
	@RequestMapping
	public String index() {
		return "loginUser";
	}

	/**
	 * ログイン処理をするメソッド.
	 * @param form ログイン画面で記入されたフォーム内のデータ
	 * @param result　
	 * @param redirectAttributes
	 * @param model
	 * @return　商品一覧画面に飛ぶ、エラー発生時はログイン画面に戻る
	 */
	@RequestMapping(value = "/login")
	public String login(@Validated LoginUserForm form, BindingResult result, RedirectAttributes redirectAttributes, 
			@ModelAttribute("user") UserPage before, Model model) {
		
		/** エラーチェック */
		
		if (result.hasErrors()) {
			FieldError error = new FieldError("emailError", "email", "");
			FieldError error2 = new FieldError("passwordError", "password", "");
			result.addError(error);
			result.addError(error2);
			return index();
		}
		
		String beforePage = before.getBeforePage();
		
		/** DBでユーザー情報チェック */
		
		UserPage user = loginUserService.execute(form, result, model);

		if (user == null) {
//			FieldError error = new FieldError("emailError", "email", "");
//			FieldError error2 = new FieldError("passwordError", "password", "");
//			result.addError(error);
//			result.addError(error2);
			
			ObjectError error = new ObjectError("loginError", "メールアドレスまたはパスワードが違います。");
			result.addError(error);
			
			return index();
		}
		
		/** ユーザー情報をセッションに入れて、商品一覧画面へ */
		
		model.addAttribute("user", user);
		redirectAttributes.addFlashAttribute("user", user);
		if (("/makePayment").equals(beforePage)) {
			return "redirect:/makePayment/";
		}
		return "redirect:/serchItem/";
	}
}
