package jp.co.rakus.ecommers.web;


import lombok.Data;

/**
 * 商品IDのリクエストパラメータが入るフォーム.
 * @author sakikoyama
 *
 */
@Data
public class ShowItemDetailForm {
	/**商品ID*/
	private Long id;
}
