package jp.co.rakus.ecommers.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.web.ViewShoppingCartPage;


@Service
@SessionAttributes("orderList")
public class ViewShoppingCartService {
	
	public ViewShoppingCartPage showCart(ArrayList<OrderItem> orderItemList){
		ViewShoppingCartPage page = new ViewShoppingCartPage();
		page.setOrderItemList(orderItemList);
		
		return page;
	}
}
