package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.page.LoginAdminPage;
import jp.co.rakus.ecommers.repository.AdminRepository;
import jp.co.rakus.ecommers.web.LoginAdminForm;

/**
 * 管理者のログイン関連のサービスクラス
 * @author rksuser
 *
 */
@Service
public class LoginAdminService {

	@Autowired
	private AdminRepository repository;

	/**
	 * 入力データで検索.
	 * @param form 入力値のデータ
	 * @return　検索結果
	 */
	public LoginAdminPage execute(LoginAdminForm form) {
		AdminUser user = repository.findByEmailAndPassword(form);
		LoginAdminPage page = new LoginAdminPage();
		if (user == null) {
			page = null;
		} else {
			page.setLoginName(user.getName());
		}
		return page;
	}
}
