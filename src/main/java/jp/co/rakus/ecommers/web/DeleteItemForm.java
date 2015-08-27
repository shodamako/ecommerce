package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * 商品削除リクエストを受け取るフォーム.
 * @author sakikoyama
 *
 */
@Data
public class DeleteItemForm {
	/**商品ID*/
	private Long id;
	/**商品の表示・非表示切り替え*/
	private Boolean deleted;
}
