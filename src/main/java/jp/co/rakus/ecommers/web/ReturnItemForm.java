package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * 商品再表示リクエストを受け取るフォーム.
 * @author sakikoyama
 *
 */
@Data
public class ReturnItemForm {
	/**商品ID*/
	private Long id;
	/**商品の表示・非表示切り替え*/
	private Boolean deleted;
}
