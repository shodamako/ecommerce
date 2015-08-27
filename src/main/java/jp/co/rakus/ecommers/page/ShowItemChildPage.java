package jp.co.rakus.ecommers.page;

import lombok.Data;

/**
 * 商品情報を持つページ.
 * @author Reo Okumura
 */
@Data
public class ShowItemChildPage {
	/** ID */
	private Long id;
	/** 商品名 */
	private String name;
	/** 単価 */
	private Integer price;
	/** 画像パス */
	private String imagePath;
	/** deletedフラグ */
	private String color;
}
