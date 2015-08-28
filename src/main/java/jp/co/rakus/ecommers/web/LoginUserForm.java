package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * ユーザーログイン用のフォーム
 * @author ShoKodama
 *
 */
@Data
public class LoginUserForm {

	/** ユーザーのメールアドレス */
	@NotEmpty(message="メールアドレスを入力してください")
	@Email(message="emailはメールアドレスとして不正です")
	private String email;
	/** ユーザーのパスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
}
