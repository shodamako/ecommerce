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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.RegisterUserService;

/**
 * ユーザー情報登録をするコントローラー
 * @author ShoKodama
 *
 */
@Controller
@RequestMapping("/registerUser")
@Transactional
public class RegisterUserController {
	
	@Autowired
	public RegisterUserService registerUserService;
	
	/**
	 * フォームを初期化する
	 * @return
	 */
	@ModelAttribute
	public RegisterUserForm setUpForm(){
		return new RegisterUserForm();
	}
	
	/**
	 * ユーザー情報登録画面を表示する
	 * @return　ユーザー情報登録画面に推移
	 */
	@RequestMapping("/input")
	public String input(){
		return "user/registerUser";
	}
	
	/**
	 * ユーザー情報登録をする
	 * @param form　フォーム記入情報
	 * @param result　エラー判定用
	 * @param model　モデル
	 */
	@RequestMapping("/create")
	public String create(@Validated RegisterUserForm form,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			Model model){
		
		/** 入力値チェック */
		
		if(result.hasErrors()){
			FieldError error = new FieldError("nameError", "name", "");
			FieldError error2 = new FieldError("emailError", "email", "");
			FieldError error3 = new FieldError("passwordError", "password", "");
			FieldError error4 = new FieldError("confirmPasswordError", "confirmPassword", "");
			FieldError error5 = new FieldError("addressError", "address", "");
			FieldError error6 = new FieldError("telephoneError", "telephone", "");
			result.addError(error);
			result.addError(error2);
			result.addError(error3);
			result.addError(error4);
			result.addError(error5);
			result.addError(error6);
			
			return input();
		}
		
		/** メールアドレス重複チェック */
		
		String email = form.getEmail();
		User checkUser = registerUserService.findByEmail(email);
		if(!(checkUser == null)){
			ObjectError error = new ObjectError("emailError", "そのアドレスはすでに使われています");
			result.addError(error);
			return input();
		}
		
		/** 再パスワード一致チェック */
		
		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();
		if(!(password.equals(confirmPassword))){
			ObjectError error = new ObjectError("passwordError", "パスワードが一致しません");
			result.addError(error);
			return input();
		}
		
		/** 登録 */
		
		UserPage page = registerUserService.execute(form, result, model);
		
		model.addAttribute("page", page);
		redirectAttributes.addFlashAttribute("page", page);
//		return "loginUser/login";
		return "redirect:/loginUser/login";
	}

}
