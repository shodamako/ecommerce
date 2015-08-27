package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザーログイン用のフォーム
 * @author ShoKodama
 *
 */
@Data
public class LoginUserForm {

	/** ユーザーのメールアドレス */
	@NotBlank(message="メールアドレスの入力情報は不正です")
	@Email(message="メールアドレスの入力情報は不正です")
	private String email;
	/** ユーザーのパスワード */
	@NotBlank(message="パスワードの入力情報は不正です")
	private String password;
}
