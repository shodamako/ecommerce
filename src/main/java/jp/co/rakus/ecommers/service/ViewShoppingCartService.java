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
	
	/**
	 * 現在のショッピングカート内商品の一覧を表示するメソッド.
	 * @param orderItemList セッションスコープ内にある現在のカート内アイテムのリスト
	 * @param model
	 * @return　セッションスコープ内にある現在のカート内アイテムのリストを返す
	 */
	public ViewShoppingCartPage showCart(ArrayList<OrderItem> orderItemList, Model model){
		ViewShoppingCartPage page = new ViewShoppingCartPage();
		page.setOrderItemList(orderItemList);
		
		return page;
	}
}
