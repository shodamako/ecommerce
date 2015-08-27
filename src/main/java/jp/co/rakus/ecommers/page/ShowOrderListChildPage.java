package jp.co.rakus.ecommers.page;

import java.util.Date;

import lombok.Data;

/**
 * 注文情報を持つページ
 * @author yuya.asari
 *
 */
@Data
public class ShowOrderListChildPage {
	private Long id;
	/** 注文番号　*/
	private String orderNumber;
	/**　日付*/
	private Date date;
	/**ユーザー名*/
	private String name;
	/**ステータス*/
	private Integer status;
	/**総計*/
	private Integer totalPrice;
}
