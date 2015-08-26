package jp.co.rakus.ecommers.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.OrderRepository;

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
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH ) + 1;
		int day = cal.get(cal.DAY_OF_MONTH);
		
		orderNumber = 21;
		DecimalFormat df1 = new DecimalFormat("000000");
		
		int total = 0;
		for(OrderItem items : orderItem){
			total += items.getItem().getPrice() * items.getQuantity() * 1.08;
		}
		Order order = new Order();
		order.setUserId(user.getId());
		order.setStatus(1);
		order.setOrderNumber(df1.format(orderNumber));
		order.setTotalPrice(total);
		order.setOrderItemList(orderItem);
		
		orderRepository.insertOrder(order);
		orderRepository.insertOrderItem(orderItem);	
	}
}
