package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 決済画面表示用Page
 * @author takayuki.honma
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentPage {
	public static final int PORT_COST = 500;
	
	/**	購入商品リスト */
	private List<MakePaymentChildPage> childPage;
	private Integer tax;
	private Integer portCost = PORT_COST;
	private Integer totalPrice;
	private String name;
	private String email;
	private String address;
	private String telephone;

}
