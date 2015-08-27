package jp.co.rakus.ecommers.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.page.ShowOrderListChildPage;
import jp.co.rakus.ecommers.page.ShowOrderListPage;
import jp.co.rakus.ecommers.repository.OrderRepository;

/**
 * 	注文一覧を取得するサービス.
 * @author yuya.asari
 *
 */
@Service
public class ShowOrderlistService {
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * @return
	 */
	public ShowOrderListPage execute(){
		ShowOrderListPage showOrderListPage = new ShowOrderListPage();
		List<ShowOrderListChildPage> showOrderchildPagelist = new ArrayList<>();
		List<Order> orderList = orderRepository.findAll();
		for(Order order : orderList){
			ShowOrderListChildPage childPage = new ShowOrderListChildPage();
			BeanUtils.copyProperties(order, childPage);
			showOrderchildPagelist.add(childPage);
		}
		showOrderListPage.setOrderChildPage(showOrderchildPagelist);
		return showOrderListPage;
	
	}

}
