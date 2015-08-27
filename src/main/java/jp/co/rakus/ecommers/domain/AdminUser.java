package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者のデータ.
 * @author rksuser
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {

	/**
	 * ID.
	 */
	private Long id;
	/**
	 * 管理者名.
	 */
	private String name;
	/**
	 * 管理者のメールアドレス.
	 */
	private String email;
	/**
	 * 管理者のパスワード.
	 */
	private String password;
	
	/**
	 * IDの入力値が数字以外の場合に使う
	 */
	private String idRegisterCheck;
}
