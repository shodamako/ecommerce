package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class LoginUserForm {

	/** ユーザーのメール */

	private String email;
	private String password;
}
