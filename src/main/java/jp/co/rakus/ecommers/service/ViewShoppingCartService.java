package jp.co.rakus.ecommers.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.web.ViewShoppingCartPage;

/**
 * ショッピングカート表示のクラス.
 * @author ShoKodama
 *
 */
@Service
public class ViewShoppingCartService {
	
	public ViewShoppingCartPage showCart(ArrayList<OrderItem> orderItemList, Model model){
		ViewShoppingCartPage page = new ViewShoppingCartPage();
		page.setOrderItemList(orderItemList);
		
		return page;
	}
}
