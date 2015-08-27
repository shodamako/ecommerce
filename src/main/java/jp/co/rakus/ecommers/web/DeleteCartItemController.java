package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.service.DeleteCartItemService;

/**
 * カート商品削除のコントローラークラス.
 * @author ShoKodama
 *
 */
@Controller
@Transactional
@RequestMapping("/deleteCartItem")
@SessionAttributes("orderItemList")
public class DeleteCartItemController {
	
	@Autowired
	private DeleteCartItemService deleteCartItemService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String delete(@ModelAttribute("orderItemList")ArrayList<OrderItem> orderItemList, 
			Long itemId,
			RedirectAttributes redirectattributes,
			Model model){
					
		deleteCartItemService.execute(orderItemList, itemId, model);
		
		return "redirect:/cart";
		
	}
	
}
