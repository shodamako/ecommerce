package jp.co.rakus.ecommers.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
@Repository
public class OrderRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
//	private static final ResultSetExtractor<List<Order>> ORDER_ROW_MAPPER = (rs) -> {
//		if (!rs.next()) {
//			return null;
//		}
//		List<Order> orderList = new ArrayList<>();
//		Integer tempId = 0;
//		List<OrderItem> orderItemList = null;
//		while (rs.next()) {
//			if (!(tempId == rs.getLong("id"))) {
//				Order order = new Order();
//				order.setId(rs.getLong("id"));
//				order.setOrderNumber(rs.getString("order_number"));
//				order.setUserId(rs.getLong("user_id"));
//				order.setStatus(rs.getInt("status"));
//				order.setTotalPrice(rs.getInt("total_price"));
//				orderList.add(order);
//				orderItemList = new ArrayList<OrderItem>();
//				order.setOrderItemList(orderItemList);
//			}
//			OrderItem orderItem = new OrderItem();
//			orderItem.setId(rs.getLong("id"));
//			orderItem.setItemId(rs.getLong("item_id"));
//			orderItem.setQuantity(rs.getInt("quantity"));
//			orderItem.setOrderId(rs.getLong("order_id"));
//			orderItem.setItem(new Item());
//			orderItemList.add(orderItem);
//			tempId = rs.getInt("id");
//			
//		}
//		return orderList;
//	};
	
	
	public void insertOrder(Order order){
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		jdbcTemplate.update("INSERT INTO orders(order_number, user_id, status, total_price) values(:orderNumer, :userId, :status, totalPrice)", param);
	}
	
	public void insertOrderItem(ArrayList<OrderItem> orderItem){
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		jdbcTemplate.update("INSERT INTO order_items(item_id, order_id, quantity) values(:itemId, :orderId, :quantity)", param);
	}
}
