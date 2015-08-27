package jp.co.rakus.ecommers.common;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttributes;
/**
 * ログインチェックを行うクラス.
 * @author Reo Okumura
 */
@SessionAttributes("page")
public class LoginCheck {
	/**
	 * ログインチェックメソッド.
	 * @param session セッション
	 * @return ログイン時はfalse,未ログイン時はtrueを返す
	 */
	public Boolean loginCheck(HttpSession session){
		try {
			if(!session.getAttribute("page").equals(null)){
				return false;
			}
		} catch (Exception e) {
			return true;
		}
		return true;
	}
}
