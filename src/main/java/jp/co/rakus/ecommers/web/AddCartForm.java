package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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
	@Range(max=1000, message="注文数の上限は1000個です")
	@Min(1)
	private Integer quantity;
	
}
