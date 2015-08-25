package jp.co.rakus.ecommers.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文Entity
 * @author yuya.asari
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	/**注文ID*/
	private Long id;
	/**注文番号*/
	private String orderNumber;
	/**ステータス*/
	private Integer status;
	/**注文リスト*/
	private List orderItemList;
	/**総計*/
	private Integer totalPrice;
	/**日付*/
	private Date date;
}
