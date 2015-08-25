package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザEntity
 * @author sakikoyama
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	/**ユーザID*/
	private Long id;
	/**ユーザ名*/
	private String name;
	/**ユーザメールアドレス*/
	private String email;
	/**ユーザパスワード*/
	private String password;
	/**ユーザアドレス*/
	private String address;
	/**ユーザ電話番号*/
	private String telephone;
}
