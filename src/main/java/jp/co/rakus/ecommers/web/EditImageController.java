package jp.co.rakus.ecommers.web;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.service.EditImageService;

/**
 * 商品画像を編集するコントローラ.
 * @author sakikoyama
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/Admin/EditImage", method = RequestMethod.POST)
public class EditImageController {
	@Autowired
	ServletContext context;
	@Autowired
	private EditImageService editImageService;

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
	 * 商品情報を更新し、商品詳細画面へ遷移.
	 * 
	 * @param editForm
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String editItem(@Validated EditItemForm editItemForm, BindingResult result,
			@RequestParam("image") MultipartFile image, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) throws Exception{
		
		LoginCheck lc = new LoginCheck();
		if (lc.loginCheck(session)) {
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/Admin";
		}
		
		if(image.isEmpty()){
			FieldError error = new FieldError("editItemForm","image","ファイルを選択してください");
			result.addError(error);
			model.addAttribute("editItemForm", editItemForm);
			return "/AdminItem/editItem";
		}
		
		Long id = editItemForm.getId();
		String imagePath = image.getOriginalFilename();
		image.transferTo(new File(context.getRealPath("/img/")+imagePath));
		ShowItemDetailForm showItemDetailForm = editImageService.execute(id,imagePath);
		
		redirectAttributes.addFlashAttribute("showItemDetailForm", showItemDetailForm);
		return "redirect:/Admin/ShowItemDetail";
	}
}
