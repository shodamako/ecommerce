package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterUserForm {
	/**ユーザ名*/
	@NotBlank
	private String name;
	/**ユーザメールアドレス*/
	@NotBlank
	@Email(message="アドレスが不正です")
	private String email;
	/**ユーザパスワード*/
	@NotBlank
	private String password;
	/**ユーザ再パスワード*/
	@NotBlank
	private String confirmPassword;
	/**ユーザ住所*/
	@NotBlank
	private String address;
	/**ユーザ電話番号*/
	@NotBlank
	private String telephone;
}
