package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class UserPage {
	/**ユーザID*/
	private Long id;
	/**ユーザ名*/
	private String name;
	/**ユーザメールアドレス*/
	private String email;
	/**ユーザアドレス*/
	private String address;
	/**ユーザ電話番号*/
	private String telephone;
}
