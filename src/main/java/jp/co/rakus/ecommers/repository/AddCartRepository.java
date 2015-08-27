package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Item;

/**
 * ショッピングカートへ商品を追加するためのRepositoryクラス.
 * 
 * @author rksuser
 *
 */
@Repository
public class AddCartRepository {

	/**
	 * データベース情報をJavaのクラスに入れ替えるメソッド.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		Integer price = rs.getInt("price");
		String imagePath = rs.getString("imagePath");
		Boolean deleted = rs.getBoolean("deleted");
		return new Item(id, name, description, price, imagePath, deleted);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * itemsテーブルより主キーのidを元に商品情報を取得するメソッド.
	 * @param id ショッピングカートに追加する商品ID
	 * @return 商品情報
	 */
	public Item findbyId(Long id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = jdbcTemplate.queryForObject(
				"select id, name, description, price, imagePath, deleted from items where id =:id", param,
				ITEM_ROW_MAPPER);
		return item;

	}

}