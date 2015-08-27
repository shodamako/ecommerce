package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.DeleteItemForm;
import jp.co.rakus.ecommers.web.ShowItemDetailForm;

/**
 * 商品を削除するサービス.
 * 
 * @author sakikoyama
 *
 */
@Service
public class DeleteItemService {
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品を削除.
	 * @param editForm
	 * @return showForm
	 */
	public ShowItemDetailForm execute(DeleteItemForm deleteItemForm){
		if(deleteItemForm.getDeleted() == false){
			deleteItemForm.setDeleted(true);
		}else{
			return null;
		}
		
		Long id = itemRepository.updateDeleted(deleteItemForm.getId(), deleteItemForm.getDeleted());
		
		ShowItemDetailForm showItemDetailForm = new ShowItemDetailForm();
		showItemDetailForm.setId(id);
		return showItemDetailForm;
	}
}
