package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import jp.co.rakus.ecommers.domain.OrderItem;
import lombok.Data;

/**
 * カート内商品のリスト用ページクラス.
 * @author ShoKodama
 *
 */
@Data
public class ViewShoppingCartPage {
	private ArrayList<OrderItem> orderItemList;
}
