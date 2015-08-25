package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * 商品詳細ページクラス.
 * @author ueno
 *
 */
@Data
public class ShowItemPage {
	/** 商品ID */
	Long id;
	/** 商品名 */
	String name;
	/** 商品説明 */
	String description;
	/** 商品価格 */
	Integer price;
}
