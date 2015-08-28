package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * ユーザー情報登録のフォーム.
 * @author ShoKodama
 *
 */
@Data
public class RegisterUserForm {
	/**ユーザ名*/
	@NotEmpty(message="お名前を入力してください")
	private String name;
	/**ユーザメールアドレス*/
	@NotNull(message="アドレスを入力してください")
	@Email(message="アドレスが不正です")
	private String email;
	/**ユーザパスワード*/
	@NotBlank(message="パスワードを入力してください")
	@Size(min=8, max=16, message="パスワードは8文字以上16文字以下で登録してください")
	private String password;
	/**ユーザ再パスワード*/
	@NotBlank(message="確認用パスワードを入力してください")
	@Size(min=8, max=16, message="確認用パスワードは8文字以上16文字以下で登録してください")
	private String confirmPassword;
	/**ユーザ住所*/
	@NotBlank(message="住所を入力してください")
	private String address;
	/**ユーザ電話番号1*/
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp = "^\\d{2,4}$", message="電話番号の記入方法が違います。0-9の数字のみを使用してください。")
	private String telephone1;
	/**ユーザ電話番号2*/
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp = "^\\d{3,5}$", message="電話番号の記入方法が違います。0-9の数字のみを使用してください。")
	private String telephone2;
	/**ユーザ電話番号3*/
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp = "^\\d{3,5}$", message="電話番号の記入方法が違います。0-9の数字のみを使用してください。")
	private String telephone3;
}
