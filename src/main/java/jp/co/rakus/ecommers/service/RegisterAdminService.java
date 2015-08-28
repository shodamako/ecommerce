package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.repository.AdminRepository;
import jp.co.rakus.ecommers.web.RegisterAdminForm;

/**
 * 管理者登録関連のサービスクラス.
 * @author rksuser
 *
 */
@Service
public class RegisterAdminService {
	
	@Autowired
	private AdminRepository repository;
	
	public List<AdminUser> findAll(){
		return repository.findAll();
	}
	
	
	/**
	 * Email検索.
	 * @param form 入力値情報
	 * @return 検索結果
	 */
	public AdminUser findByEmail(RegisterAdminForm form){
		return repository.findByEmail(form);
	}
	
	/**
	 * 入力値を登録.
	 * @param form 入力値情報
	 */
	public void insert(RegisterAdminForm form){
		repository.insert(form);
	}
}
