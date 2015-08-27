package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterUserForm {
	/**ユーザ名*/
	@NotBlank(message="お名前を入力してください")
	private String name;
	/**ユーザメールアドレス*/
	@NotBlank(message="アドレスを入力してください")
	@Email(message="アドレスが不正です")
	private String email;
	/**ユーザパスワード*/
	@NotBlank(message="パスワードを入力してください")
	private String password;
	/**ユーザ再パスワード*/
	@NotBlank(message="確認用パスワードを入力してください")
	private String confirmPassword;
	/**ユーザ住所*/
	@NotBlank(message="住所を入力してください")
	private String address;
	/**ユーザ電話番号*/
	@NotBlank(message="電話番号を入力してください")
	private String telephone;
}