package jp.co.rakus.ecommers.web;

import java.util.ArrayList;

import jp.co.rakus.ecommers.domain.OrderItem;
import lombok.Data;

@Data
public class ViewShoppingCartPage {
	private ArrayList<OrderItem> orderItemList;
}
