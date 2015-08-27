package jp.co.rakus.ecommers.repository;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		return new Order(id, orderNumber, userId, status, null, totalPrice);
	};
	
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
		jdbcTemplate.update("INSERT INTO orders(order_number, user_id, status, total_price) values(:orderNumber || to_char(nextval('order_number_count'),'000000'), :userId, :status, :totalPrice)", param);
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
}
