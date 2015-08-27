package jp.co.rakus.ecommers.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.OrderItem;

/**
 * カート商品削除用のサービスクラス.
 * @author ShoKodama
 *
 */
@Service
@SessionAttributes("orderItemlist")
public class DeleteCartItemService {
	
	/**
	 * カート商品削除のメソッド.
	 * @param itemId　削除したい商品のID
	 */
	public void execute(ArrayList<OrderItem> orderItemList, Long itemId, Model model){
				
		for(int i = 0; i < orderItemList.size(); i++){
			
			OrderItem orderItem = orderItemList.get(i);
			Long checkId = orderItem.getItemId();		
			
			if( checkId == itemId){
				orderItemList.remove(i);
				break;					
			}
		}
		
		model.addAttribute("orderItemlist", orderItemList);
	}
}
