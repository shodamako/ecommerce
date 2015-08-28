package jp.co.rakus.ecommers.repository;

//<<<<<<< HEAD
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
//=======
//import java.util.Date;
//>>>>>>> feature/order

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
/**
 * orders,order_items操作用リポジトリ.
 * @author masashi.ueno
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;
	private static Long orderId;
	
//	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
//		Long id = rs.getLong("id");
//		String orderNumber = rs.getString("order_number");
//		Long userId = rs.getLong("user_id");
//		Integer status = rs.getInt("status");
//		Integer totalPrice = rs.getInt("total_price");
//		return new Order(id, orderNumber, userId, status, null, totalPrice);
//	};
	
	@PostConstruct
	public void init(){
		insert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations()).withTableName("orders").usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 注文情報を登録する.
	 * @param order 注文情報
	 */
	public void insertOrder(Order order){
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		Number key = insert.executeAndReturnKey(param);
		orderId = key.longValue();
		
		order.setId(orderId);
		param = new BeanPropertySqlParameterSource(order);
		jdbcTemplate.update("DELETE FROM orders WHERE id=:id", param);
		jdbcTemplate.update("INSERT INTO orders(order_number, user_id, status, total_price, date) values(:orderNumber || to_char(nextval('order_number_count'),'000000'), :userId, :status, :totalPrice,:date)", param);
		++orderId;
	}
	
	
	public Order getSequence(Order order){
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		Order item = jdbcTemplate.queryForObject("SELECT id, order_number, user_id, status, total_price FROM orders;", param, ORDER_ROW_MAPPER);
		return item;
	}
	
	/**
	 * 注文商品情報を登録する.
	 * @param orderItem 注文商品情報
	 */
	public void insertOrderItem(OrderItem orderItem){
		orderItem.setOrderId(orderId);
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		jdbcTemplate.update("INSERT INTO order_items(item_id, order_id, quantity) values(:itemId, :orderId, :quantity)", param);
		
	}
	/**
	 * ResultSetオブジェクトからOrderオブジェクトに変換するためのクラス実装とインスタンス化
	 */
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		Date date = rs.getDate("date");
		String name = rs.getString("name");
		return new Order(id, orderNumber, userId, status, totalPrice,date,name);
	};

	
	/**
	 * 注文一覧を取得するメソッド.
	 * @return
	 */
	public List<Order> findAll() {
		List<Order> orderList = jdbcTemplate.query(
				"SELECT orders.id,order_number,user_id,status,total_price,date,name FROM orders INNER JOIN users ON user_id = users.id ORDER BY orders.id ",ORDER_ROW_MAPPER);
		if(orderList == null){
			return null;
		}else{
		return orderList;
		}
	}
	
	private static final ResultSetExtractor<Order> orderRsetExtractordetail = (rs) -> {
		rs.next();
		Long id = rs.getLong("orders_id");
		String orderNumber = rs.getString("orders_order_number");
		Long userId = rs.getLong("orders_user_id");
		Integer status = rs.getInt("orders_status");
		Integer totalPrice = rs.getInt("orders_total_price");
		Date date = rs.getDate("orders_date");
		User user = new User(rs.getLong("users_id"), rs.getString("users_name"), rs.getString("users_email"),
				rs.getString("users_password"), rs.getString("users_address"), rs.getString("users_telephone"));

		List<OrderItem> orderItemList = new ArrayList<>();
		// List<Item> itemList = new ArrayList<>();

		do {
			Item item = new Item(rs.getLong("items_id"), rs.getString("items_name"), rs.getString("items_description"),
					rs.getInt("items_price"), rs.getString("items_imagepath"), rs.getBoolean("items_deleted"));
			// itemList.add(item);
			OrderItem orderItem = new OrderItem(rs.getLong("order_items_id"), rs.getLong("order_items_item_id"),
					rs.getInt("order_items_quantity"),rs.getLong("order_items_order_id"), item);
			orderItemList.add(orderItem);

			// List<Item> newItemList = new ArrayList<>();
			// item = new Item(rs.getLong("items_id"),
			// rs.getString("items_name"), rs.getString("items_description"),
			// rs.getInt("items_price"), rs.getString("items_imagepath"),
			// rs.getBoolean("items_deleted"));
			// newItemList.add(item);
			// orderItem = new OrderItem(rs.getLong("order_items_id"),
			// rs.getLong("order_items_item_id"),
			// rs.getLong("order_items_order_id"),
			// rs.getInt("order_items_quantity"), newItemList);
			// orderItemList.add(orderItem);
		} while (rs.next());

		Order order = new Order(id, orderNumber, userId, status, orderItemList, totalPrice, date, user);
		return order;
	};

	/**
	 * idから詳細ページを作る.
	 * 
	 * @param id
	 *            オーダーID
	 * @return ShowOrderDetailPageへ返す
	 */
	public Order findById(Long id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String sql = "select orders.id orders_id, orders.order_number orders_order_number, orders.user_id orders_user_id, orders.status orders_status, orders.total_price orders_total_price, orders.date orders_date,users.id users_id, users.name users_name, users.email users_email, users.password users_password, users.address users_address, users.telephone users_telephone,order_items.id order_items_id, order_items.item_id order_items_item_id, order_items.order_id order_items_order_id, order_items.quantity order_items_quantity,items.id items_id, items.name items_name, items.description items_description, items.price items_price, items.imagepath items_imagepath, items.deleted items_deleted from orders left outer join users on orders.user_id = users.id left outer join order_items on orders.id = order_items.order_id left outer join items on order_items.item_id = items.id where orders.id = :id order by orders.id";
		Order order = jdbcTemplate.query(sql, param, orderRsetExtractordetail);
		return order;
	}

	/**
	 * ResultSetオブジェクトからItemオブジェクトに変換するためのクラス実装とインスタンス化
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

	/**
	 * 注文詳細を取得するメソッド.
	 * @param itemId
	 * @return
	 */
	public List<Item> findByItemId(Long itemId) {
		SqlParameterSource param = new MapSqlParameterSource("itemId", itemId);
		List<Item> itemList = jdbcTemplate.query(
				"select id,name,description,price,imagePath,deleted from items where id = :itemId", param,
				ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * ステータス更新.
	 * @param id　オーダーID
	 * @param status
	 */
	public void updateStatus(Long id, Integer status) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("status", status);
		String sql = "update orders set status = :status WHERE id = :id";
		jdbcTemplate.update(sql, param);
	}
}