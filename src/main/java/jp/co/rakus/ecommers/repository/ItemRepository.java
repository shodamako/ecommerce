package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Item;

/**
 * 商品関連リポジトリクラス.
 * @author ueno
 *
 */
@Repository
public class ItemRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) ->{
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		Integer price = rs.getInt("price");
		String imagePath = rs.getString("imagePath");
		Boolean deleted = rs.getBoolean("deleted");
		
		return new Item(id, name, description, price, imagePath,deleted);
	};
	
	/**
	 * id値の商品情報を取得.
	 * @param id id値
	 * @return 検索された商品情報
	 */
	public Item findById(Long id){
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = jdbcTemplate.queryForObject("SELECT id, name, description, price, imagePath, deleted FROM items WHERE id=:id", param, ITEM_ROW_MAPPER);
		return item;
	}

}
