package jp.co.rakus.ecommers.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Item;

/**
 * 商品関連リポジトリクラス.
 * 
 * @author masashi.ueno
 *
 */
@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		Integer price = rs.getInt("price");
		String imagePath = rs.getString("imagePath");
		Boolean deleted = rs.getBoolean("deleted");

		return new Item(id, name, description, price, imagePath, deleted);
	};
	private static final RowMapper<Item> ITEM_ID_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		Item item = new Item();
		item.setId(id);
		return item;
	};

	/**
	 * id値の商品情報を取得.
	 * 
	 * @param id
	 *            id値
	 * @return 検索された商品情報
	 */
	public Item findById(Long id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			Item item = jdbcTemplate.queryForObject(
					"SELECT id, name, description, price, imagePath, deleted FROM items WHERE id=:id", param,
					ITEM_ROW_MAPPER);
			return item;
		} catch (DataAccessException e) {
			return null;
		}
	};

	/**
	 * 商品一覧を取得するメソッド.
	 * 
	 * @return itemList
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,description,price,imagePath,deleted FROM items ORDER BY id DESC";
		List<Item> itemList = jdbcTemplate.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * データベースのアイテムを全件取得 deletedがtrueの場合はリストから除外する
	 * 
	 * @return アイテムのリスト
	 */
	public List<Item> findAllByFalse() {
		List<Item> items = new ArrayList<>();
		List<Item> allItems = jdbcTemplate.query(
				"SELECT id, name, description, price, imagePath, deleted FROM items ORDER BY price", 
				ITEM_ROW_MAPPER);

		for (Item item : allItems) {
			if (item.getDeleted()) {
				continue;
			}
			items.add(item);
		}
		return items;
	}

	/**
	 * キーワードにあてはまるアイテムを取得 deletedがtrueの場合はリストから除外する
	 * 
	 * @param word
	 *            キーワード
	 * @return アイテムのリスト
	 */
	public List<Item> findByKeywordAndFalse(String word) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("word", word);
		List<Item> items = new ArrayList<>();
		List<Item> allItems = jdbcTemplate.query(
				"SELECT id, name, description, price, imagePath, deleted FROM items WHERE name LIKE '%' || :word || '%' ORDER BY price", 
				param, 
				ITEM_ROW_MAPPER);

		for (Item item : allItems) {
			if (item.getDeleted()) {
				continue;
			}
			items.add(item);
		}
		return items;
	}

	/**
	 * 商品検索をするメソッド.
	 * 
	 * @param keyword
	 *            検索ワード
	 * @return 該当商品一覧情報を持ったListオブジェクト
	 */
	public List<Item> findByKeyword(String keyword) {
		String sql = "SELECT id,name,description,price,imagePath,deleted FROM items WHERE name ILIKE :keyword ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("keyword", keyword);
		List<Item> itemList = jdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	/**
	 * 商品検索をするメソッド.
	 * 
	 * @param keyword
	 *            検索ワード
	 * @return 該当商品一覧情報を持ったListオブジェクト
	 */
	public List<Item> findByKeywordComplete(String keyword) {
		String sql = "SELECT id,name,description,price,imagePath,deleted FROM items WHERE name = :keyword ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("keyword", keyword);
		List<Item> itemList = jdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品名から商品情報を取得.
	 * 
	 * @return 商品情報.商品情報が存在しない場合はnull
	 */
	public Item findByName(String name) {
		String sql = "SELECT id,name,description,price,imagePath,deleted FROM items WHERE name = :name ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
		Item item = jdbcTemplate.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * 商品詳細検索をするメソッド
	 * 
	 * @param keyword
	 *            検索ワード
	 * @param price
	 *            値段
	 * @return 該当商品一覧情報を持ったListオブジェクト
	 */
	public List<Item> findByKeywordAndPrice(String keyword, Integer price) {
		String sql = "SELECT id,name,description,price,imagePath,deleted FROM items WHERE name like :keyword AND price <= :price ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("keyword", keyword).addValue("price", price);
		List<Item> itemList = jdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品情報を追加するメソッド.
	 * 
	 * @param item
	 *            追加商品の情報を持ったオブジェクト
	 */
	public void insert(Item item) {
		String sql = "INSERT INTO items (id,name,description,price,imagePath) VALUES (:id,:name,:description,:price,:imagePath)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		jdbcTemplate.update(sql, param);
	}

	/**
	 * 商品IDが一致する商品情報をimagePath以外更新.
	 * 
	 * @param item
	 * @return 商品情報.更新に失敗した場合はnull
	 */
	public Item update(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "UPDATE items SET name = :name,description = :description,price = :price,deleted = :deleted WHERE id = :id";
		jdbcTemplate.update(sql, param);
		return item;
	}

	/**
	 * 商品IDが一致するDeletedを切り替え.
	 * 
	 * @param id
	 * @param deleted
	 * @return 商品ID.更新に失敗した場合はnull
	 */
	public Long updateDeleted(Long id, Boolean deleted) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("deleted", deleted);
		String sql = "UPDATE items SET deleted = :deleted WHERE id = :id";
		jdbcTemplate.update(sql, param);
		return id;
	}

	/**
	 * 商品IDの最大値を検索するメソッド.
	 * 
	 * @return maxId
	 */
	public List<Item> findMaxId() {
		String sql = "SELECT id FROM items";
		List<Item> itemList = jdbcTemplate.query(sql, ITEM_ID_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品IDが一致する商品のimagePathを更新.
	 * 
	 * @param item
	 * @return 商品情報.更新に失敗した場合はnull
	 */
	public Long updateImagePath(Long id,String imagePath){
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("imagePath", imagePath);
		String sql = "UPDATE items SET imagePath = :imagePath WHERE id = :id";
		jdbcTemplate.update(sql, param);
		return id;
	}
}
