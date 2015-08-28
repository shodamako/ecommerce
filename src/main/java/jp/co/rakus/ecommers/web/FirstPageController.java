package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;

/**
 * アイテム一覧表示を行うコントローラー.
 * @author takayuki.honma
 *
 */
@Controller
@RequestMapping("/")
@Transactional
@SessionAttributes({"orderItemlist", "user"})
public class FirstPageController {
	/**
	 * @param model　モデル
	 * @return　
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(SerchItemForm form, Model model) {
		ArrayList<OrderItem> orderItemList = new ArrayList<>();
		UserPage user = new UserPage();
		model.addAttribute("orderItemlist", orderItemList);
		model.addAttribute("user", user);
		return "forward:/serchItem/";
	}

}
