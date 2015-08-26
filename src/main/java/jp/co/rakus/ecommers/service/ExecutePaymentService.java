package jp.co.rakus.ecommers.service;

import java.util.ArrayList;

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
	
	/**
	 * 注文情報を登録する.
	 * @param orderItem 注文商品
	 * @param user ユーザ情報
	 */
	public void insert(ArrayList<OrderItem> orderItem, User user){
		Order order = new Order();
		order.setUserId(user.getId());
		order.setStatus(1);
		order.setOrderItemList(orderItem);
		
		orderRepository.insertOrder(order);
		orderRepository.insertOrderItem(orderItem);	
	}
}
