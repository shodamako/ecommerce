package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.service.RegisterAdminService;

/**
 * 管理者登録.
 * @author rksuser
 *
 */
@Controller
@RequestMapping("/Admin/registShow")
public class RegisterAdminController {

	@Autowired
	private RegisterAdminService service;
	
	/**
	 * formの初期化.
	 * @return
	 */
	@ModelAttribute
	public RegisterAdminForm setUpForm(){
		return new RegisterAdminForm();
	}
	
	/**
	 * 管理者登録画面に遷移.
	 * @return
	 */
	@RequestMapping()
	public String index(HttpSession session,RedirectAttributes redirectAttributes,Model model){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		Long adminUserNum = (long) (service.findAll().size()+1);
		model.addAttribute("adminUserNum", adminUserNum);
		return "./Admin/registerAdmin";
	}
	
	/**
	 * 管理者登録の処理。エラーがある場合は表示させる.
	 * @param form 入力したデータ
	 * @param result エラーのデータ
	 * @param model スコープ
	 * @return　エラーがある場合は、登録画面に戻りエラー表示。ない場合はリダイレクトして、メニュー画面へ
	 */
	@RequestMapping("/register")
	public String registerAdmin(@RequestParam Long id,@Validated RegisterAdminForm form,BindingResult result,Model model,HttpSession session,RedirectAttributes redirectAttributes){
		form.setId(id);
		if(result.hasErrors()){
			return index(session,redirectAttributes,model);
		}
		AdminUser user = service.findById(form);
		if(user != null&&user.getIdRegisterCheck() != null){
			ObjectError error = new ObjectError("idError", "IDは数字で入力して下さい");
			result.addError(error);
			return index(session,redirectAttributes,model);
		}else if(user != null){	
			ObjectError error = new ObjectError("idError", "そのIDはすでに使われています");
			result.addError(error);
			return index(session,redirectAttributes,model);
		}
		if(!(form.getPassword().equals(form.getConfirmationPass()))){
			ObjectError error = new ObjectError("passwordError", "パスワードが一致しませんでした");
			result.addError(error);
			return index(session,redirectAttributes,model);
		}
		user = service.findByEmail(form);
		if(user != null){
			ObjectError error = new ObjectError("emailError", "そのアドレスはすでに使われています");
			result.addError(error);
			return index(session,redirectAttributes,model);
		}
		service.insert(form);
		return "redirect:/Admin/showMenu";
	}
	
}
