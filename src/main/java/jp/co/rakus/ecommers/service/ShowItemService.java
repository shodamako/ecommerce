package jp.co.rakus.ecommers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.ShowItemPage;

/**
 * 商品詳細関連サービスクラス.
 * @author ueno
 *
 */
@Service
public class ShowItemService {
	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * id値の商品情報を取得.
	 * @param id id値
	 * @return 検索された商品情報
	 */
	public ShowItemPage execute(Long id){
		ShowItemPage itempage = new ShowItemPage();
		Item item = itemRepository.findById(id);
		BeanUtils.copyProperties(item, itempage);
		return itempage;
	}
}
