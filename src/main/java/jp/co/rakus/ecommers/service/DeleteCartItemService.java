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
	public void execute(ArrayList<OrderItem> orderItemlist, Long itemId, Model model){
				
<<<<<<< HEAD
		for(int i = 0; i > orderItemlist.size(); i++){
=======
		for(int i = 0; i < orderItemList.size(); i++){
>>>>>>> 809194d5d36ab19fc3e44baa8b4f7b52321e13eb
			
			OrderItem orderItem = orderItemlist.get(i);
			Long checkId = orderItem.getItemId();		
			
			if( checkId == itemId){
				orderItemlist.remove(i);
				break;					
			}
		}
		
<<<<<<< HEAD
		model.addAttribute("orderItemlist", orderItemlist);
=======
		model.addAttribute("orderItemlist", orderItemList);
>>>>>>> 809194d5d36ab19fc3e44baa8b4f7b52321e13eb
	}
}
