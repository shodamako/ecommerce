package jp.co.rakus.ecommers.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.repository.OrderRepository;
import jp.co.rakus.ecommers.web.UserPage;

/**
 * 注文関連サービスクラス.
 * @author masashi.ueno
 *
 */
@Service
public class ExecutePaymentService {
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文情報を登録する.
	 * @param orderItem 注文商品
	 * @param user ユーザ情報
	 */
	public void insert(ArrayList<OrderItem> orderItem, UserPage user){
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = cal.getTime();
				
		int total = 0;
		for(OrderItem items : orderItem){
			total += items.getItem().getPrice() * items.getQuantity() * 1.08;
		}
		
		Order order = new Order();
		order.setUserId(user.getId());
		order.setStatus(1);
		order.setOrderNumber(sdf.format(date));
		
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
