package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 商品詳細情報関連のリクエストパラメータが入るフォーム.
 * @author sakikoyama
 *
 */
@Data
public class EditItemForm {
	/**商品ID*/
	private Long id;
	/**商品名*/
	@NotEmpty(message = "商品名は必須です")
	@Size(min=2,max=127,message = "商品名は2文字以上127文字以下で入力してください")
	private String name;
	/**商品説明*/
	@NotEmpty(message = "説明は必須です")
	@Size(min=2,max=127,message = "説明は2文字以上127文字以下で入力してください")
	private String description;
	/**商品価格*/
	@NotNull(message = "単価は必須です")
	@Min(value=1,message = "価格は1円以上に設定してください。")
	@Max(value=500000,message = "価格は500,000円以下に設定してください。")
	private Integer price;
	/**商品イメージファイルパス*/
	private String imagePath;
	/**商品表示・非表示切り替え*/
	private Boolean deleted;
	/**商品イメージ画像ファイル*/
	private MultipartFile image;
}