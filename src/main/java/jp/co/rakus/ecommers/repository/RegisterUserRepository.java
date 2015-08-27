package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.User;

/**
 * ユーザー登録用のレポジトリ
 * 
 * @author ShoKodama
 *
 */
@Repository
public class RegisterUserRepository {

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
//		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String address = rs.getString("address");
		String telephone = rs.getString("telephone");

		return new User(null, name, email, password, address, telephone);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * 新規登録ユーザーのアドレス重複チェック
	 * 
	 * @param email　新規登録ユーザーのアドレス
	 * @return 重複の場合はユーザー情報取得、なければnullを返す
	 */
	public User findByEmail(String email) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		User user = null;

		try {
			user = jdbcTemplate.queryForObject(
					"SELECT id, name, email, password, address, telephone FROM users WHERE email = :email", param,
					USER_ROW_MAPPER);
			return user;
		} catch (DataAccessException e) {
			return user;
		}
	}

	/**
	 * ユーザー情報登録・更新
	 * 
	 * @param user　新規登録ユーザー情報
	 * @return 新規登録ユーザー情報
	 */
	public User save(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		try {
			
			jdbcTemplate.update(
					"INSERT INTO users (name, email, password, address, telephone) VALUES (:name, :email, :password, :address, :telephone)",
					param);
			User userData = new User();
			userData = jdbcTemplate.queryForObject("SELECT id, name, email, address, telephone FROM users WHERE email = :email", 
					param, 
					USER_ROW_MAPPER);
			
			return userData;
		} catch (DataAccessException e) {
			return user;
		}
	}

}
