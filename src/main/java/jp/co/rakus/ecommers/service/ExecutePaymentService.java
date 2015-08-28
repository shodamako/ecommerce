package jp.co.rakus.ecommers.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("orderItemlist")
public class ExecutePaymentService {
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文情報を登録する.
	 * @param orderItem 注文商品
	 * @param user ユーザ情報
	 */
	public void insert(ArrayList<OrderItem> orderItemlist, UserPage user){
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat orderSDF = new SimpleDateFormat("yyyyMMdd");
		Date orderNumber = cal.getTime();
		
//		SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd");
		Date date = cal.getTime();
		int total = 0;
		for(OrderItem items : orderItemlist){
			total += items.getItem().getPrice() * items.getQuantity() * 1.08;
		}
		
		Order order = new Order();
		order.setUserId(user.getId());
		order.setStatus(1);
		order.setOrderNumber(orderSDF.format(orderNumber));
		order.setDate(date);
		order.setTotalPrice(total);
		order.setOrderItemList(orderItemlist);
		
		orderRepository.insertOrder(order);
		
		for(OrderItem item : orderItemlist){
			orderRepository.insertOrderItem(item);
		}
	}
	
	/**
	 * ショッピングカートのアイテム情報を消去する.
	 * @param orderItemlist 注文商品一覧
	 * @param model　モデル
	 */
	public void deleteCart(ArrayList<OrderItem> orderItemlist,Model model){
		orderItemlist.removeAll(orderItemlist);
		model.addAttribute("orderItemlist", orderItemlist);
	}
}
