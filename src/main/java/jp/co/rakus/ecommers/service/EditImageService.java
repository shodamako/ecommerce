package jp.co.rakus.ecommers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.EditItemForm;

/**
 * 商品画像を編集するサービス.
 * @author sakikoyama
 *
 */
@Service
public class EditImageService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品画像を登録.
	 * @param editForm
	 * @return　showForm
	 */
	public EditItemForm execute(Long id,String imagePath){
		itemRepository.updateImagePath(id,imagePath);
		Item item = itemRepository.findById(id);
		EditItemForm editItemForm = new EditItemForm();
		BeanUtils.copyProperties(item, editItemForm);
		return editItemForm;
	}
}
