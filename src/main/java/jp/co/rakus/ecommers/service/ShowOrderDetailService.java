package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.repository.OrderRepository;
import jp.co.rakus.ecommers.web.ShowOrderDetailForm;

@Service
public class ShowOrderDetailService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * IDから注文詳細情報を取得.
	 * @param showForm
	 * @return 商品詳細情報
	 */
	public Order findById(ShowOrderDetailForm showOrderDetailForm){
		Order order = orderRepository.findById(showOrderDetailForm.getId());
		return order;
	}
	
	public List<Item> findbyItemId(Long itemId){
		return orderRepository.findByItemId(itemId);
	}
	public void updateStatus(Long id, Integer status) {
		orderRepository.updateStatus(id, status);
	}
}
