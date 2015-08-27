package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 登録情報.
 * @author rksuser
 *
 */
@Data
public class RegisterAdminForm {

	/**
	 * ID.
	 */
	private Long id;
	/**
	 * 名前。入力してない場合はエラー表示.
	 */
	@NotEmpty(message="お名前を入力して下さい")
	private String name;
	/**
	 * メールアドレス。入力してないかEmail形式でない場合にエラー表示.
	 */
	@NotEmpty(message="メールアドレスを入力して下さい")
	@Email(message="Eメール形式(***@***)で入力して下さい")
	private String email;
	/**
	 * パスワード。入力してない場合エラー表示.
	 */
	@Length(min=8,max=16,message="パスワードは8文字以上16文字以下で入力してください")
	private String password;
	/**
	 * 確認用パスワード。入力してない場合にエラー表示.
	 */
	@NotEmpty(message="確認用パスワードを入力して下さい")
	private String confirmationPass;
}
