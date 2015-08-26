package jp.co.rakus.ecommers.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 決済内容一覧表示用Page
 * @author takayuki.honma
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentChildPage {
	private String name;
	private Integer price;
	private Integer quantity;
	private Integer taxPrice;
	private Integer sumPrice;

}
