package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * 商品検索用フォーム.
 * @author Reo Okumura
 */
@Data
public class ShowItemForm {
	/** 
	 * 検索ID
	 * null=全件,1=曖昧,2=完全,3=値段
	 */
	private Integer id;
	/** 検索ワード */
	private String keyword;
	/** 値段 */
	private String price;
}
