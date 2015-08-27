package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;

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
	@NotNull(message="個数を指定してください")
	private Integer quantity;
	
}
