package jp.co.rakus.ecommers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.web.LoginAdminForm;
import jp.co.rakus.ecommers.web.RegisterAdminForm;

/**
 * 管理者関連のリポジトリクラス.
 * @author rksuser
 *
 */
@Repository
public class AdminRepository {
	@Autowired
	NamedParameterJdbcTemplate temp;
	/**
	 * ResultSetオブジェクトからAdminUserオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<AdminUser> ADMIN_USER_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String idRegisterCheck = null;
		return new AdminUser(id, name, email, password,idRegisterCheck);
	};

	/**
	 * 管理者全件検索.
	 * @return 検索結果
	 */
	public List<AdminUser> findAll(){
		List<AdminUser> adminUserList = temp.query("SELECT id,name,email,password From admin_users",ADMIN_USER_ROW_MAPPER);
		return adminUserList;
	}
	/**
	 * 入力データから検索.
	 * @param form 入力情報
	 * @return 検索結果
	 */
	public AdminUser findByEmailAndPassword(LoginAdminForm form) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		try {
			AdminUser adminUser = temp.queryForObject("SELECT id,name,email,password FROM admin_users WHERE email = :email",param, ADMIN_USER_ROW_MAPPER);
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			if(encoder.matches(form.getPassword(), adminUser.getPassword())){
				return adminUser;
			}else{
				return null;
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	/**
	 * ID検索.
	 * @param form 入力値情報
	 * @return 検索結果
	 */
	public AdminUser findById(RegisterAdminForm form){
			try{
				SqlParameterSource param = new BeanPropertySqlParameterSource(form);
				AdminUser adminUser = temp.queryForObject("SELECT id,name,email,password FROM admin_users WHERE id = :id;", param, ADMIN_USER_ROW_MAPPER);
				return adminUser;
			}catch(EmptyResultDataAccessException e){
				return null;
			}
	}
	
	/**
	 * Email検索.
	 * @param form 入力値情報
	 * @return 検索結果
	 */
	public AdminUser findByEmail(RegisterAdminForm form) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		try{
			AdminUser adminUser = temp.queryForObject("SELECT id,name,email,password FROM admin_users WHERE email = :email;", param, ADMIN_USER_ROW_MAPPER);
			return adminUser;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	/**
	 * 入力値を登録する.
	 * @param form 入力値情報
	 */
	public void insert(RegisterAdminForm form){
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String encodePassword = encoder.encode(form.getPassword());
		form.setPassword(encodePassword);
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		temp.update("INSERT INTO admin_users(id,name,email,password) VALUES(:id,:name,:email,:password)", param);
	}
}
