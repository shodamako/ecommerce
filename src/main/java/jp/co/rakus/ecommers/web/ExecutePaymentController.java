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

@Controller
@Transactional
@RequestMapping(value = "/executePayment")
public class ExecutePaymentController {
	
	@Autowired
	private ExecutePaymentService executePaymentService;
	
	@RequestMapping(value = "insert")
	public String insert(@ModelAttribute("orderItemList") ArrayList<OrderItem> orderItem, @ModelAttribute("user") User user){
		executePaymentService.insert(orderItem, user);
		return "finishedPayment";
	}

}
