package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.ExecutePaymentService;

/**
 * 注文関連操作を行うコントローラー.
 * @author masashi.ueno
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/executePayment")
public class ExecutePaymentController {
	
	@Autowired
	private ExecutePaymentService executePaymentService;
	
	@RequestMapping
	public String test(){
		return "tesut";
	}
	
	
	/**
	 * 注文情報を登録する.
	 * @param orderItem 注文商品情報
	 * @param user ユーザ情報
	 * @return 決済完了画面
	 */
	@RequestMapping(value = "insert")
	public String insert(@ModelAttribute("orderItemList") ArrayList<OrderItem> orderItem, @ModelAttribute("user") User user){
		executePaymentService.insert(orderItem, user);
		return "redirect:/executePayment/finished";
	}
	
	/**
	 * 決済完了画面へ遷移する.
	 * @return 決済完了画面
	 */
	@RequestMapping(value = "/finished")
	public String finishedPayment(){
		return "finishedPayment";
	}

}
