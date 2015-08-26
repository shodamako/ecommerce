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
	private Long id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** 商品価格 */
	private Integer price;
	/**商品イメージファイルパス*/
	private String imagePath;
}
