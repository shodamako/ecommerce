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
	
	private User user;
	public Order(Long id, String orderNumber, Long userId, Integer status, Integer totalPrice,
			Date date,String name) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.userId=userId;
		this.status=status;
		this.totalPrice = totalPrice;
		this.date=date;
		this.name=name;
	}
	public Order(Long id, String orderNumber, Long userId, Integer status, List<OrderItem> orderItemList,
			Integer totalPrice, Date date,User user) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.userId=userId;
		this.status=status;
		this.orderItemList=orderItemList;
		this.totalPrice = totalPrice;
		this.date=date;
		this.user=user;
	}
	/**注文ID*/
	private Long id;
	/**注文番号*/
	private String orderNumber;
	/**ユーザーID*/
	private Long userId;
	/**ステータス*/
	private Integer status;
	/**注文リスト*/
	private List<OrderItem> orderItemList;
	/**総計*/
	private Integer totalPrice;
	/**日付*/
	private Date date;
	/**ユーザー名*/
	private String name;
}