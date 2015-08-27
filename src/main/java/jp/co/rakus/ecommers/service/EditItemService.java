package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.ShowItemDetailForm;

/**
 * 商品詳細情報を編集するサービス.
 * @author sakikoyama
 *
 */
@Service
public class EditItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品詳細情報を上書き登録.
	 * @param editForm
	 * @return　showForm
	 */
	public ShowItemDetailForm execute(Item item){
		itemRepository.update(item);
		ShowItemDetailForm showItemDetailForm = new ShowItemDetailForm();
		showItemDetailForm.setId(item.getId());
		return showItemDetailForm;
	}
	
	public Item execute(String name){
		return itemRepository.findByName(name);
	}
}
