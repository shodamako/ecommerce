package jp.co.rakus.ecommers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.EditItemForm;
import jp.co.rakus.ecommers.web.ShowItemDetailForm;

/**
 * 商品詳細情報を扱うサービス.
 * @author sakikoyama
 *
 */
@Service
public class ShowItemDetailService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品IDから商品詳細情報を取得.
	 * @param showForm
	 * @return 商品詳細情報
	 */
	public EditItemForm execute(ShowItemDetailForm showItemDetailForm){
		Item item = itemRepository.findById(showItemDetailForm.getId());
		EditItemForm editItemForm = new EditItemForm();
		BeanUtils.copyProperties(item, editItemForm);
		return editItemForm;
	}
}
