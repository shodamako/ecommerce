package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotBlank
	private Integer quantity;
}
