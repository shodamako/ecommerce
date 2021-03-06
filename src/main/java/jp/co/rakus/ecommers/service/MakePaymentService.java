package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.web.MakePaymentChildPage;
import jp.co.rakus.ecommers.web.MakePaymentPage;
import jp.co.rakus.ecommers.web.UserPage;

@Service
public class MakePaymentService {
	public static final double TAX_RATE = 0.08;
	/**
	 * キーワードにあてはまるアイテムを取得
	 * @param form　検索フォーム
	 * @return　ページ情報のリスト
	 */

	public MakePaymentPage execute(ArrayList<OrderItem> orderItemList, UserPage user) {
		MakePaymentPage page = new MakePaymentPage();
		List<MakePaymentChildPage> mpcpList = new ArrayList<>();
		Integer tax = 0;
		Integer totalPrice = 0;
		
		for (OrderItem order : orderItemList) {
			Item item = order.getItem();
			MakePaymentChildPage mpcp = new MakePaymentChildPage();
			mpcp.setName(item.getName());
			mpcp.setPrice(item.getPrice());
			mpcp.setQuantity(order.getQuantity());
			mpcp.setImagePath(item.getImagePath());
			Integer taxPrice = (int)(item.getPrice() * order.getQuantity() * (1 + TAX_RATE));
			mpcp.setTaxPrice(taxPrice);
			tax += (int)(item.getPrice() * order.getQuantity() * TAX_RATE);
			totalPrice += taxPrice;
			mpcp.setSumPrice(item.getPrice() * order.getQuantity());
			
			mpcpList.add(mpcp);
		}
		
		page.setChildPage(mpcpList);
		page.setTax(tax);
		page.setTotalPrice(totalPrice + page.getPortCost());
		
		page.setName(user.getName());
		page.setEmail(user.getEmail());
		page.setAddress(user.getAddress());
		page.setTelephone(user.getTelephone());
		
		return page;
	}

}
