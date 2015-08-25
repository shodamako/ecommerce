package jp.co.rakus.ecommers.web;

import java.util.List;

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
public class SerchItemPage {
	private List<SerchItemChildPage> childPage;

}
