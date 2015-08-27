package jp.co.rakus.ecommers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.repository.OrderRepository;
import jp.co.rakus.ecommers.web.EditItemForm;
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
	public EditItemForm execute(ShowOrderDetailForm showOrderDetailForm){
		Order order = orderRepository.findById(showOrderDetailForm.getId());
		EditItemForm editItemForm = new EditItemForm();
		
		BeanUtils.copyProperties(order, editItemForm);
		return editItemForm;
	}
}
