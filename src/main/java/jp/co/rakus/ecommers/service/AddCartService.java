package jp.co.rakus.ecommers.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("orderItemlist")
public class AddCartService {
	@Autowired
	private AddCartRepository addCartRepository;

	/**
	 * ショッピングカートへ商品を追加するメソッド.
	 * @param form ショッピングカートへ追加する商品情報
	 * @param orderItemList ショッピングカート内の商品リスト
	 * @return 商品リスト
	 */
	public ArrayList<OrderItem> addCart(Model model, AddCartForm form, ArrayList<OrderItem> cartItemList) {
		Item item = addCartRepository.findbyId(form.getItemId());
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setItemId(form.getItemId());
		orderItem.setQuantity(form.getQuantity());
		
		int index = 0;
		for (OrderItem cartItem : cartItemList) {
			if (orderItem.getItemId().equals(cartItem.getItemId())) {
				OrderItem updateItem = cartItemList.get(index);
				Integer quantity = updateItem.getQuantity();
				updateItem.setQuantity(quantity + orderItem.getQuantity());
				return cartItemList;
			}
			++index;
		}
		
		cartItemList.add(orderItem);
		
		model.addAttribute("orderItemlist", cartItemList);
		
		return cartItemList;
		
	}
}
