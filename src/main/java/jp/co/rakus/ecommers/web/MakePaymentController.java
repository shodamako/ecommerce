package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class MakePaymentController {
	
	@Autowired
	private MakePaymentService makePaymentService;
	
	/**
	 * @param form　検索フォーム
	 * @param model　モデル
	 * @return　
	 */
	@RequestMapping(value = "/")
	public String list(Model model, @ModelAttribute("orderItemList") ArrayList<OrderItem> orderItemList, 
			@ModelAttribute("user") UserPage user) {
		MakePaymentPage makePaymentPage = makePaymentService.execute(orderItemList, user);
		model.addAttribute("makePaymentPage", makePaymentPage);
		return "/makePayment";
	}
//	@RequestMapping(value ="/")
//	public String list(Model model){
//		MakePaymentPage makePaymentPage = new MakePaymentPage();
//		MakePaymentChildPage child = new MakePaymentChildPage();
//		child.setName("aa");
//		child.setPrice(100);
//		child.setQuantity(3);
//		child.setTaxPrice(200);
//		child.setSumPrice(200);
//		List<MakePaymentChildPage> list = new ArrayList<>();
//		list.add(child);
//		makePaymentPage.setTax(100);
//		makePaymentPage.setTotalPrice(100);
//		makePaymentPage.setName("aaa");
//		makePaymentPage.setEmail("aa@aa");
//		makePaymentPage.setAddress("土浦");
//		makePaymentPage.setTelephone("080");
//		makePaymentPage.setChildPage(list);
//		model.addAttribute("makePaymentPage", makePaymentPage);
//		return "/makePayment";
//	}

}
