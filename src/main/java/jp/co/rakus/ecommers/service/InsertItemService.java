package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;

/**
 * 商品追加をするサービス.
 * @author Reo Okumura
 */
@Service
public class InsertItemService {
	@Autowired
	private ItemRepository itemRepository;
	/**
	 * 実行メソッド.
	 * @param form フォーム
	 */
	public Boolean execute(Item item){
		try {
			if(itemRepository.findByName(item.getName())!=null){
				return false;
			}
		} catch (Exception e) {
			
		}
		itemRepository.insert(item);
		return true;
	}
	/**
	 * 商品IDの最大値を検索するメソッド.
	 * @return maxId
	 */
	public Integer findMaxId(){
		List<Item> itemList = itemRepository.findMaxId();
		Integer maxItemId = 0;
		for(Item item : itemList){
			Integer itemId = new Integer(item.getId().toString());
			if(maxItemId < itemId){
				maxItemId = itemId;
			}
		}
		return maxItemId;
	}
}
