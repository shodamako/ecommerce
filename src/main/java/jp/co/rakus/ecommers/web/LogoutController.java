package jp.co.rakus.ecommers.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Transactional
@RequestMapping("/logout")
@SessionAttributes(types={UserPage.class, Order.class})
public class LogoutController {

	@RequestMapping("/")
	public String logout(SessionStatus sessionStatus){
		sessionStatus.setComplete();
		return "/list";
	}
}