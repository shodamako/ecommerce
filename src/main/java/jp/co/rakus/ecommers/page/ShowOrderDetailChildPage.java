package jp.co.rakus.ecommers.page;

import lombok.Data;

/**
 * 注文詳細情報を持つチャイルドページ.
 * @author yuya.asari
 *
 */
@Data
public class ShowOrderDetailChildPage {
	
	/**商品名 */
	private String name;
	/**価格*/
	private Integer price;
	/**個数*/
	private Integer quantity;
	/**小計*/
	private Integer totalPrice;
}
