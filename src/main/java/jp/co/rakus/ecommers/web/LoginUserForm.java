package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;

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
	@NotEmpty(message="メールアドレスの入力情報は不正です")
<<<<<<< HEAD
	@Email(message="メールアドレスの入力情報は不正です")
=======
	@Email(message="emailはメールアドレスとして不正です")
>>>>>>> e734b45654491b8336e7523d2b4dff8638b34055
	private String email;
	/** ユーザーのパスワード */
	@NotBlank(message="パスワードの入力情報は不正です")
	private String password;
}
