package jp.co.rakus.ecommers.page;

import lombok.Data;

@Data
public class ShowOrderDetailChildPage {
	
	/**商品名 */
	private String name;
	/**価格*/
	private Integer price;
	/**個数*/
	private Integer count;
	/**金額*/
	private Integer totalPrice;

}
