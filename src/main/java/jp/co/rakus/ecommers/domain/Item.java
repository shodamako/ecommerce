
package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品Entity
 * 
 * @author sakikoyama
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	/** 商品ID */
	private Long id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** 商品価格 */
	private Integer price;
	/** 商品イメージファイルパス */
	private String imagePath;
	/** 商品表示・非表示切り替え */
	private Boolean deleted;
}
