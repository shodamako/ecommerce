package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class LoginUserForm {

	private String mailAddress;
	private String password;
}
