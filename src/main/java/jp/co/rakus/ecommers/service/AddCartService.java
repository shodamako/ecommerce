package jp.co.rakus.ecommers.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.repository.AddCartRepository;
import jp.co.rakus.ecommers.web.AddCartForm;

/**
 * ショッピングカートへ商品を追加するServiceクラス.
 * @author rksuser
 *
 */
@Service
public class AddCartService {
	@Autowired
	private AddCartRepository addCartRepository;

	/**
	 * ショッピングカートへ商品を追加するメソッド.
	 * @param form ショッピングカートへ追加する商品情報
	 * @param orderItemList ショッピングカート内の商品リスト
	 * @return 商品リスト
	 */
	public ArrayList<OrderItem> addCart(Long itemId, Integer quantity, ArrayList<OrderItem> orderItemList) {
		Item item = addCartRepository.findbyId(itemId);
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setItemId(itemId);
		orderItem.setQuantity(quantity);
		if (orderItemList == null) {
			ArrayList<OrderItem> newOrderItemList = new ArrayList<>();
			newOrderItemList.add(orderItem);
			return newOrderItemList;
		}
		orderItemList.add(orderItem);
		return orderItemList;
	}
}
