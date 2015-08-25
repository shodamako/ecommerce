package jp.co.rakus.ecommers.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * アイテム一覧表示用Page
 * @author takayuki.honma
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerchItemChildPage {
	private Long id;
	private String name;
	private Integer price;
	private String imagePath;

}
