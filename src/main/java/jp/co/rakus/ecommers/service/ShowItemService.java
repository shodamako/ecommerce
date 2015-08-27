package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.page.AdminShowItemPage;
import jp.co.rakus.ecommers.page.ShowItemChildPage;
import jp.co.rakus.ecommers.repository.ItemRepository;
import jp.co.rakus.ecommers.web.ShowItemForm;
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
	/**
	 * 実行メソッド.
	 * @param form フォーム
	 * @return 商品一覧情報を持ったShowItemPageオブジェクト
	 */
	public AdminShowItemPage execute(ShowItemForm form) {
		AdminShowItemPage showItemPage = new AdminShowItemPage();
		List<ShowItemChildPage> childPageList = new ArrayList<>();
		String msg = "";
		if (form.getId()==null) {
			List<Item> itemList = itemRepository.findAll();
			for (Item item : itemList) {
				ShowItemChildPage childPage = new ShowItemChildPage();
				BeanUtils.copyProperties(item, childPage);
				if(item.getDeleted()){
					childPage.setColor("#fa8072");
				}
				childPageList.add(childPage);
			}
		} else if (form.getId()==1){
			String keyword = "%" + form.getKeyword() + "%";
			List<Item> itemList = itemRepository.findByKeyword(keyword);
			if(itemList.isEmpty()){
				msg = "該当する商品がありません";
				itemList = itemRepository.findAll();
			}
			for (Item item : itemList) {
				ShowItemChildPage childPage = new ShowItemChildPage();
				BeanUtils.copyProperties(item, childPage);
				if(item.getDeleted()){
					childPage.setColor("#fa8072");
				}
				childPageList.add(childPage);
			}
		} else if (form.getId()==2){
			List<Item> itemList = itemRepository.findByKeyword(form.getKeyword());
			if(itemList.isEmpty()){
				msg = "該当する商品がありません";
				itemList = itemRepository.findAll();
			}
			for (Item item : itemList) {
				ShowItemChildPage childPage = new ShowItemChildPage();
				BeanUtils.copyProperties(item, childPage);
				if(item.getDeleted()){
					childPage.setColor("#fa8072");
				}
				childPageList.add(childPage);
			}
		} else if (form.getId()==3){
			String keyword = "%" + form.getKeyword() + "%";
			List<Item> itemList = itemRepository.findByKeywordAndPrice(keyword,form.getPrice());
			if(itemList.isEmpty()){
				msg = "該当する商品がありません";
				itemList = itemRepository.findAll();
			}
			for (Item item : itemList) {
				ShowItemChildPage childPage = new ShowItemChildPage();
				BeanUtils.copyProperties(item, childPage);
				if(item.getDeleted()){
					childPage.setColor("#fa8072");
				}
				childPageList.add(childPage);
			}
			if (form.getPrice() == null) {
				msg = "値段を設定してください";
			}
		}
		showItemPage.setMsg(msg);
		showItemPage.setChildPage(childPageList);
		return showItemPage;
	}
}
