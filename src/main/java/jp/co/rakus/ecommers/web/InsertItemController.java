package jp.co.rakus.ecommers.web;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.service.InsertItemService;

/**
 * 商品追加をするコントローラー.
 * @author Reo Okumura
 */
@Controller
@Transactional
@SessionAttributes("page")
public class InsertItemController {
	@Autowired
	ServletContext context;
	@Autowired
	private InsertItemService insertItemService;
	/**
	 * フォーム初期化.
	 * @return フォーム
	 */
	@ModelAttribute
	public InsertItemForm setUpForm(){
		return new InsertItemForm();
	}
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1000000 * 1024 * 1024);
        factory.setMaxRequestSize(1000000 * 1024 * 1024);
        return factory.createMultipartConfig();
    }
	/**
	 * アップロードファイル容量制限.
	 * @return
	 */
	private int FILE_CAPACITY_LIMIT() {
		return 5120000;
	}
	/**
	 * 商品追加画面.
	 * @return 商品追加画面
	 */
	@RequestMapping(value="/Admin/InsertItem")
	public String insertItem(HttpSession session,RedirectAttributes redirectAttributes,Model model){
		LoginCheck lc = new LoginCheck();
		if(lc.loginCheck(session)){
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error",error);
			return "redirect:/Admin";
		}
		Integer maxId = insertItemService.findMaxId() + 1;
		model.addAttribute("maxId",maxId);
		return "/AdminItem/insert";
	}
	/**
	 * 商品追加.
	 * @param form フォーム
	 * @return 商品一覧画面
	 */
	@RequestMapping(value="/Admin/Insert",method=RequestMethod.POST)
	public String insert(
			@Validated InsertItemForm form,
			BindingResult result,
//			@RequestParam("file") MultipartFile multipartFile,
			Model model,
			RedirectAttributes redirectAttributes
			) throws Exception{
		if (result.hasErrors()) {
			return "/AdminItem/insert";
		}
		if (form.getFile().isEmpty()) {
			FieldError error = new FieldError("insertItemForm", "file", "ファイルを選択してください");
			result.addError(error);
			return "/AdminItem/insert";
		}
		if (form.getFile().getSize() >= FILE_CAPACITY_LIMIT()) {
			FieldError error = new FieldError("editItemForm", "file", "画像ファイルは5120KB以下の画像を選択してください。");
			result.addError(error);
			return "/AdminItem/insert";
		}
		MultipartFile multipartFile = form.getFile();
		try {
			Integer price = new Integer(form.getPrice());
			if (price < 1 || price > 500000) {
				FieldError error = new FieldError("insertItemForm", "price", "単価は１円以上500,000円以下に設定してください");
				result.addError(error);
				return "/AdminItem/insert";
			}
		} catch (Exception e) {
			FieldError error = new FieldError("insertItemForm", "price", "単価には数字を入力してください");
			result.addError(error);
			return "/AdminItem/insert";
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		Integer price = new Integer(form.getPrice());
		item.setPrice(price);
		String fileName = multipartFile.getOriginalFilename();
		try {
			multipartFile.transferTo(new File(context.getRealPath("/img/") + fileName));
			item.setImagePath(fileName);
		} catch (Exception e) {
			FieldError error = new FieldError("insertItemForm", "file", "ファイルアップロードに予期せぬエラーが発生しました");
			result.addError(error);
			return "/AdminItem/insert";
		}
		String insert = "";
		if (insertItemService.execute(item)) {
			insert = "商品登録が完了しました";
			redirectAttributes.addFlashAttribute("insert", insert);
			return "redirect:/Admin/Item";
		}
		FieldError error = new FieldError("insertItemForm", "name", "商品名が重複しています");
		result.addError(error);
		return "/AdminItem/insert";
	}
}
