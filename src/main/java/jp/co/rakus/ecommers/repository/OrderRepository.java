package jp.co.rakus.ecommers.repository;

//<<<<<<< HEAD
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
//=======
//import java.util.Date;
//>>>>>>> feature/order

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
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
	
	@PostConstruct
	public void init(){
		insert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations()).withTableName("orders").usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 注文情報を登録する.
	 * @param order 注文情報
	 */
	public void insertOrder(Order order){
		StringBuilder sb = new StringBuilder();
		String orderNum = order.getOrderNumber();
		DecimalFormat dformat = new DecimalFormat("000000");
		sb.append(orderNum).append(dformat.format(selectNexstSeq()));
		order.setOrderNumber(sb.toString());
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		Number key = insert.executeAndReturnKey(param);
		orderId = key.longValue();
	}
	
	public Integer selectNexstSeq() {
		String sql = "SELECT nextval('order_number_count')";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		return result;
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
				"SELECT orders.id,order_number,user_id,status,total_price,date,name FROM orders INNER JOIN users ON user_id = users.id",ORDER_ROW_MAPPER);
		if(orderList == null){
			return null;
		}else{
		return orderList;
		}
	}
	/**
	 * idから詳細ページを作る.
	 * @param id　オーダーID
	 * @return ShowOrderDetailPageへ返す
	 */
	public Order findById(Long id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String sql = "select o.id o_id, o.order_number o_order_number, o.user_id o_user_id, o.status o_status, o.total_price o_total_price, o.date o_date, u.id u_id, u.name u_name, u.email u_email, u.password u_password, u.address u_address, u.telephone u_telephone, oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id, oi.quantity oi_quantity,i.id i_id, i.name i_name, i.description i_description, i.price i_price, i.imagepath i_imagepath, i.deleted i_deleted from orders o left outer join users u on o.user_id = u.id left outer join order_items oi on o.id = oi.order_id left outer join items i on oi.item_id = i.id where o.id = :id order by o.id;";
		try {
			Order order = jdbcTemplate.queryForObject(sql, param, ORDER_ROW_MAPPER);
			return order;
		} catch (DataAccessException ex) {
			return null;
		}
	}
}
