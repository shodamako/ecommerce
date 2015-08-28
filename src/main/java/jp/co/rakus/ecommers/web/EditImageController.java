package jp.co.rakus.ecommers.web;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.service.EditImageService;

/**
 * 商品画像を編集するコントローラ.
 * 
 * @author sakikoyama
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/Admin/EditImage")
public class EditImageController {
	@Autowired
	ServletContext context;
	@Autowired
	private EditImageService editImageService;
	
	//Springのファイルアップロードの制限の引き上げ
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1000000 * 1024 * 1024);
        factory.setMaxRequestSize(1000000 * 1024 * 1024);
        return factory.createMultipartConfig();
    }
	/**
	 * 編集ボタンフォーム初期化.
	 * 
	 * @return 編集ボタン
	 */
	@ModelAttribute
	public ShowItemDetailForm setUpDetailForm() {
		return new ShowItemDetailForm();
	}

	/**
	 * 商品編集フォーム初期化.
	 * 
	 * @return
	 */
	@ModelAttribute
	public EditItemForm setUpEditItemForm() {
		return new EditItemForm();
	}
	
	/**
	 * アップロードファイル容量制限.
	 * @return
	 */
	private int FILE_CAPACITY_LIMIT() {
		return 5120000;
	}
	
	/**
	 * 商品情報を更新し、商品詳細画面へ遷移.
	 * 
	 * @param editForm
	 */
	@RequestMapping()
	public String editItem(@Validated EditItemForm editItemForm, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) throws Exception {
		//ログインチェック
		LoginCheck lc = new LoginCheck();
		if (lc.loginCheck(session)) {
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/Admin";
		}
		//エラーチェック
		if (editItemForm.getImage().isEmpty()) {
			FieldError error = new FieldError("editItemForm", "image", "ファイルを選択してください。");
			result.addError(error);
			model.addAttribute("editItemForm", editItemForm);
			return "/AdminItem/editItem";
		}
		//アップロードファイル容量チェック
		if (editItemForm.getImage().getSize() >= FILE_CAPACITY_LIMIT()) {
			FieldError error = new FieldError("editItemForm", "image", "画像ファイルは5,120KB以下の画像を選択してください。");
			result.addError(error);
			model.addAttribute("editItemForm", editItemForm);
			return "/AdminItem/editItem";
		}
		//ファイル拡張子チェック
		if (!(editItemForm.getImage().getOriginalFilename().endsWith(".jpeg")) && !(editItemForm.getImage().getOriginalFilename().endsWith(".jpg"))) {
			FieldError error = new FieldError("editItemForm", "image", "jpegファイルを指定してください。");
			result.addError(error);
			model.addAttribute("editItemForm", editItemForm);
			return "/AdminItem/editItem";
		}
		
		
		Long id = editItemForm.getId();
		String imagePath = editItemForm.getImage().getOriginalFilename();
		editItemForm.getImage().transferTo(new File(context.getRealPath("/img/") + imagePath));
		ShowItemDetailForm showItemDetailForm = editImageService.execute(id, imagePath);

		redirectAttributes.addFlashAttribute("showItemDetailForm", showItemDetailForm);
		return "redirect:/Admin/ShowItemDetail";
	}
}
