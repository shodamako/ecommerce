package jp.co.rakus.ecommers.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.ShowOrderlistService;

/**
 * 注文一覧を表示するコントローラ.
 * @author yuya.asari
 *
 */
@Controller
@Transactional
@RequestMapping(value="/Admin/orderList")
public class ShowOrderListController {
	@Autowired
	ShowOrderlistService showOrderlistService;
	
	@RequestMapping
	public String list(Model model){
		model.addAttribute("orderpage",showOrderlistService.execute());
		return "AdminOrder/orderList";
	}
	
}
