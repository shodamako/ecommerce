package jp.co.rakus.ecommers.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowOrderDetailPage {
	/**ID*/
	private Long id;
	/** 注文番号 */
	private String no;
	/** ユーザー名 */
	private String name;
	/** メールアドレス */
	private String email;
	/** 住所 */
	private String address;
	/** TEL（電話番号） */
	private String telephone;
	/** OrderDetailChildを保持するリスト */
	private List<ShowOrderDetailChildPage> ShowOrderDetailChildPageList;
	/** 小計 */
	private int totalPrice;
	/** 税 */
	private int tax;
	/** 支払い方法 */
	private String paymentMethod;
	/** 送料一律 */
	private int postage;
	/** 総計 */
	private int grandTotal;
	/** ステータス */
	private int status;
}
