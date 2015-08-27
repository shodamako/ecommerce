package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.rakus.ecommers.page.LoginAdminPage;

/**
 * ログアウト処理をするコントローラ.
 * @author rksuser
 *
 */
@Controller
@RequestMapping("/Admin/logout")
@SessionAttributes("page")
public class LogoutAdminController {

	/**
	 * ログアウト処理.
	 * @param page ログインした人の名前のデータが入っているクラス
	 * @param sessionStatus セッションスコープの情報
	 * @return リダイレクト後、ログイン画面に遷移
	 */
	@RequestMapping()
	public String logout(LoginAdminPage page,SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/Admin";
	}
}
