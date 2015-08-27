package jp.co.rakus.ecommers.page;

import java.util.List;

import lombok.Data;

/**
 * 注文情報一覧を持つページ
 * @author yuya.asari
 *
 */
@Data
public class ShowOrderListPage {
	/** 注文一覧 */
	private List<ShowOrderListChildPage> orderChildPage;
}
