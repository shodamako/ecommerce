package jp.co.rakus.ecommers.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.page.ShowOrderListPage;
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
	public String list(Model model,HttpSession session,RedirectAttributes redirectAttributes){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		ShowOrderListPage page = showOrderlistService.execute();
//		List<String> statusList = new ArrayList<>();
//		for(ShowOrderListChildPage childPage :page.getOrderChildPage() ){
//			switch (childPage.getStatus()) {
//			case 1:
//				statusList.add("未入金");
//				break;
//			case 2:
//				statusList.add("入金済み");
//				break;
//			case 3:
//				statusList.add("発想済み");
//				break;
//			case 9:
//				statusList.add("キャンセル");
//				break;
//			}
//		}
//		model.addAttribute("statusList", statusList);
		model.addAttribute("orderpage",page);
		return "AdminOrder/orderList";
	}
	
}
