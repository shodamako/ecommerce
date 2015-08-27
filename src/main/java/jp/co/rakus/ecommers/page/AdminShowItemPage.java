package jp.co.rakus.ecommers.page;

import java.util.List;

import lombok.Data;
/**
 * 商品情報一覧を持つページ.
 * @author Reo Okumura
 */
@Data
public class AdminShowItemPage {
	/** 商品一覧 */
	private List<ShowItemChildPage> childPage;
	/** メッセージ */
	private String msg;
}
