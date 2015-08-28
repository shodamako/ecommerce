package jp.co.rakus.ecommers.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.page.ShowOrderDetailPage;
import jp.co.rakus.ecommers.service.ShowOrderDetailService;

@Controller
@Transactional
@RequestMapping(value = "/Admin/ShowOrderDetail")
public class ShowOrderDetailController {
	@Autowired
	private ShowOrderDetailService showOrderDetailService;

	@ModelAttribute
	public OrderStatusForm setUpform() {
		return new OrderStatusForm();
	}

	@RequestMapping(value = "{orderId}")
	public String ShowOrderDetail(@PathVariable("orderId") Long id, ShowOrderDetailForm showOrderDetailForm,
			Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		LoginCheck lc = new LoginCheck();
		if (lc.loginCheck(session)) {
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/Admin";
		}
		showOrderDetailForm.setId(id);
		ShowOrderDetailPage showOrderDetailPage = new ShowOrderDetailPage();
		Order order = showOrderDetailService.findById(showOrderDetailForm);
		BeanUtils.copyProperties(order, showOrderDetailPage);
		Map<Integer, String> statusMap = new LinkedHashMap<>();
		statusMap.put(1, "未入金");
		statusMap.put(2, "入金済");
		statusMap.put(3, "発送済");
		statusMap.put(9, "キャンセル");
		model.addAttribute("statusMap", statusMap);
		model.addAttribute("ShowOrderDetailPage", showOrderDetailPage);
		return "/AdminOrder/orderDetail";
	}

	@RequestMapping("/UpdateStatus")
	public String UpdateStatus(@RequestParam Long id, OrderStatusForm form, Model model) {
		showOrderDetailService.updateStatus(id, form.getStatus());
		return "redirect:/Admin/ShowOrderDetail/" + id;
	}
}
