package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * ショッピングカートに追加する商品情報.
 * @author rksuser
 * 
 *
 */
@Data
public class AddCartForm {

	/**商品ID*/
	private Long itemId;
	/**個数*/
	private Integer quantity;
}
