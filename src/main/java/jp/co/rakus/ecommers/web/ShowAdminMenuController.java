package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;

@Controller
@RequestMapping("/Admin/showMenu")
public class ShowAdminMenuController {
	
	@RequestMapping()
	public String index(HttpSession session,RedirectAttributes redirectAttributes){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/Admin";
		}
		return "./Admin/administerMenu";
	}
}
