package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.ShowItemDetailForm;

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
	public ShowItemDetailForm execute(Long id,String imagePath){
		itemRepository.updateImagePath(id,imagePath);
		ShowItemDetailForm showItemDetailForm = new ShowItemDetailForm();
		showItemDetailForm.setId(id);
		return showItemDetailForm;
	}
}
