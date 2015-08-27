package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.ShowOrderDetailService;

@Controller
@Transactional
@RequestMapping(value = "/Admin/ShowOrderDetail")
public class ShowOrderDetailController {
	@Autowired
	private ShowOrderDetailService showOrderDetailService;

	/**
	 * 指定した注文の詳細情報を表示.
	 * 
	 * @param showForm
	 *            フォーム
	 * @param model
	 *            モデル
	 * @return 注文詳細ページ
	 */
	@RequestMapping(value = "")
	public String showOrderDetail(ShowOrderDetailForm showOrderDetailForm, Model model) {
		EditItemForm editOrderForm = showOrderDetailService.execute(showOrderDetailForm);
		model.addAttribute("editOrderForm", editOrderForm);
		return "AdminItem/editOrder";
	}
}
