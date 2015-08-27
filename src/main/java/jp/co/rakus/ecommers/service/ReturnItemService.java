package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.ReturnItemForm;
import jp.co.rakus.ecommers.web.ShowItemDetailForm;

@Service
public class ReturnItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品を再表示.
	 * @param returnItemForm
	 * @return
	 */
	public ShowItemDetailForm execute(ReturnItemForm returnItemForm){
		if(returnItemForm.getDeleted() == true){
			returnItemForm.setDeleted(false);
		}else{
			return null;
		}
		
		Long id = itemRepository.updateDeleted(returnItemForm.getId(), returnItemForm.getDeleted());
		
		ShowItemDetailForm showItemDetailForm = new ShowItemDetailForm();
		showItemDetailForm.setId(id);
		return showItemDetailForm;
	}
}
