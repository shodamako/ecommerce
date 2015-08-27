package jp.co.rakus.ecommers.page;

import lombok.Data;

/**
 * ログインした管理者の名前のデータを入れるクラス.
 * @author rksuser
 *
 */
@Data
public class LoginAdminPage {
	/**
	 * ログイン者の名前.
	 */
	private String loginName;
}
