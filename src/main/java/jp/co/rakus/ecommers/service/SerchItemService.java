package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.SerchItemChildPage;
import jp.co.rakus.ecommers.web.SerchItemForm;
import jp.co.rakus.ecommers.web.SerchItemPage;

@Service
public class SerchItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * キーワードにあてはまるアイテムを取得
	 * @param form　検索フォーム
	 * @return　ページ情報のリスト
	 */
	public SerchItemPage execute(SerchItemForm form) {
		SerchItemPage page = new SerchItemPage();
		List<SerchItemChildPage> items = new ArrayList<>();
		List<Item> allItems = new ArrayList<>();
		if (!("").equals(form.getKeyWord()) && !(form.getKeyWord() == null)) {
			allItems = itemRepository.findByKeyword(form.getKeyWord());
		}
		if (allItems.size() == 0) {
			allItems = itemRepository.findAll();
		}
		for (Item item : allItems) {
			SerchItemChildPage child = new SerchItemChildPage(item.getId(), item.getName(), item.getPrice(), item.getImagePath());
			items.add(child);
		}
		page.setChildPage(items);
		return page;
	}
	
	public boolean isNotExsitKeyWord(SerchItemForm form) {
		List<Item> allItems = new ArrayList<>();
		if (("").equals(form.getKeyWord()) || form.getKeyWord() == null) {
			return false;
		} else {
			allItems = itemRepository.findByKeyword(form.getKeyWord());
		}
		return allItems.size() == 0;
	}

}
