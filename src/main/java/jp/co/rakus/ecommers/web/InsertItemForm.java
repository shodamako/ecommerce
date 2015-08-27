package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 商品追加フォーム.
 * @author Reo Okumura
 */
@Data
public class InsertItemForm {
	/** ID */
	@NotNull(message = "IDは必須です")
	private Long id;
	/** 商品名 */
	@NotEmpty(message = "商品名は必須です")
	@Size(min=2,max=127,message = "商品名は2文字以上127文字以下で入力してください")
	private String name;
	/** 説明 */
	@NotEmpty(message = "説明は必須です")
	@Size(min=2,max=127,message = "説明は2文字以上127文字以下で入力してください")
	private String description;
	/** 単価 */
	@NotNull(message = "単価は必須です")
	@Size(min=1,max=6,message = "単価は1桁以上6桁以下で入力してください")
	private String price;
	/** 画像 */
	private MultipartFile file;
	
	
}
