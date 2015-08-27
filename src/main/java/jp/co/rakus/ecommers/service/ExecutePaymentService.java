package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.OrderRepository;

/**
 * 注文関連サービスクラス.
 * @author masashi.ueno
 *
 */
@Service
public class ExecutePaymentService {
	@Autowired
	private OrderRepository orderRepository;
	
	private static Integer orderNumber = 0;
	/**
	 * 注文情報を登録する.
	 * @param orderItem 注文商品
	 * @param user ユーザ情報
	 */
	public void insert(ArrayList<OrderItem> orderItem, User user){
//		Calendar cal = Calendar.getInstance();
//		Integer year = cal.get(cal.YEAR);
//		Integer month = cal.get(cal.MONTH ) + 1;
//		Integer day = cal.get(cal.DAY_OF_MONTH);
//		
//		orderNumber = 1;
//		DecimalFormat df1 = new DecimalFormat("000000");
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append(year).append(month).append(day);
//		sb.append(df1.format(orderNumber));
//		
		int total = 0;
		for(OrderItem items : orderItem){
			total += items.getItem().getPrice() * items.getQuantity() * 1.08;
		}
		Order order = new Order();
//		order.setUserId(user.getId());
		order.setUserId((long)2);
		order.setStatus(1);
		order.setOrderNumber("1");
		order.setTotalPrice(total);
		order.setOrderItemList(orderItem);
		
		orderRepository.insertOrder(order);
		
		
		for(OrderItem item : orderItem){
			orderRepository.insertOrderItem(item);
		}
		
//		
//		OrderItem item = new OrderItem();
//		item.setItemId((long)1);
//		item.setOrderId((long)1);
//		item.setQuantity(3);
//		orderRepository.insertOrderItem(item);
	}
}
