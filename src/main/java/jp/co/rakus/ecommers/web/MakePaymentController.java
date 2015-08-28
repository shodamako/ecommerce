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
		
		if (user.getEmail() == null) {
			return "forward:/loginUser";
		}
		MakePaymentPage makePaymentPage = makePaymentService.execute(orderItemList, user);
		System.out.println(makePaymentPage);
		model.addAttribute("makePaymentPage", makePaymentPage);
		return "/makePayment";
	}

}
