package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * ログイン画面の入力値データ.
 * @author rksuser
 *
 */
@Data
public class LoginAdminForm {

	/**
	 * メールアドレス。未入力か、Eメール形式で入力してない場合にエラー.
	 */
	@NotEmpty(message="メールアドレスを入力して下さい")
	@Email(message="Eメール形式(***@***)で入力して下さい")
	private String email;
	/**
	 * パスワード。未入力の場合にエラー.
	 */
	@NotEmpty(message="パスワードを入力して下さい")
	private String password;
}
