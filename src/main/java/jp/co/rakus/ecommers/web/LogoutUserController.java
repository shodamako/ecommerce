package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

/**
 * ユーザのログアウトをするControllerクラス.
 * @author rksuser
 *
 */

@Controller
@Transactional
@RequestMapping("/logout")
// @SessionAttributes(types={UserPage.class, Order.class})
public class LogoutUserController {

	/**
	 * ログイン情報の入ったセッションスコープを削除するメソッド.
	 * @param sessionStatus セッションスコープを削除するクラス
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "forward:/Top";
	}
}