package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.service.MakePaymentService;

/**
 * アイテム一覧表示を行うコントローラー.
 * @author takayuki.honma
 *
 */
@Controller
@RequestMapping("/makePayment")
@Transactional
@SessionAttributes({"orderItemlist", "user"})
public class MakePaymentController {
	
	@Autowired
	private MakePaymentService makePaymentService;
	
	/**
	 * @param form　検索フォーム
	 * @param model　モデル
	 * @return　
	 */
	@RequestMapping(value = "/")
	public String list(Model model, @ModelAttribute("orderItemlist") ArrayList<OrderItem> orderItemList, 
			@ModelAttribute("user") UserPage user) {
		MakePaymentPage makePaymentPage = makePaymentService.execute(orderItemList, user);
		model.addAttribute("makePaymentPage", makePaymentPage);
		return "/makePayment";
	}
//	@RequestMapping(value = "/")
//	public String list(Model model) {
//		
//		Item item = new Item((long)1, "PC", "test", 50000, "pc", false);
//		Item item2 = new Item((long)2, "マウス", "test", 500, "mouse", false);
//		OrderItem Oitem = new OrderItem();
//		OrderItem Oitem2 = new OrderItem();
//		Oitem.setId((long)1);
//		Oitem.setItem(item);
//		Oitem.setItemId((long)1);
//		Oitem.setOrderId((long)1);
//		Oitem.setQuantity(1);
//		Oitem2.setId((long)2);
//		Oitem2.setItem(item2);
//		Oitem2.setItemId((long)2);
//		Oitem2.setOrderId((long)1);
//		Oitem2.setQuantity(3);
//		ArrayList<OrderItem> orderItemList = new ArrayList<>();
//		orderItemList.add(Oitem);
//		orderItemList.add(Oitem2);
//		UserPage user = new UserPage();
//		user.setAddress("船堀");
//		user.setEmail("taka@rakus");
//		user.setId((long)1);
//		user.setName("本間貴幸");
//		user.setTelephone("080-1891-3356");
//		
////		UserPage user = null;
//		
//		
//		if (user == null) {
//			return "forward:/loginUser/login";
//		}
//		
//		MakePaymentPage makePaymentPage = makePaymentService.execute(orderItemList, user);
//		model.addAttribute("makePaymentPage", makePaymentPage);
//		return "/makePayment";
//	}

}
