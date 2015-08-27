package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.common.LoginCheck;
import jp.co.rakus.ecommers.domain.Item;
import jp.co.rakus.ecommers.service.EditItemService;

/**
 * 商品情報を編集するコントローラ.
 * 
 * @author sakikoyama
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/Admin/EditItem", method = RequestMethod.POST)
public class EditItemController {
	@Autowired
	private EditItemService editItemService;

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
	public String editItem(@Validated EditItemForm editItemForm, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes, Model model) {
		// ログインチェック
		LoginCheck lc = new LoginCheck();
		if (lc.loginCheck(session)) {
			String error = "不正なアクセスです。ログインからやり直してください。";
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/Admin";
		}
		// エラーチェック
		if (result.hasErrors()) {
			model.addAttribute("editItemForm", editItemForm);
			return "/AdminItem/editItem";
		}
		// 商品名重複チェック
		String name = editItemForm.getName();
		try {
			if (!(editItemService.execute(name) == null)) {
				Item item = editItemService.execute(name);
				//編集中の商品がヒットした場合はエラーを返さない
				if (!(item.getId() == editItemForm.getId())) {
					FieldError error = new FieldError("editItemForm", "name", "すでに同じ名前で商品が登録されています.");
					result.addError(error);
					model.addAttribute("editItemForm", editItemForm);
					return "/AdminItem/editItem";
				}
			}
		} catch (EmptyResultDataAccessException ex) {
		}

		Item item = new Item();
		BeanUtils.copyProperties(editItemForm, item);
		ShowItemDetailForm showItemDetailForm = editItemService.execute(item);

		redirectAttributes.addFlashAttribute("showItemDetailForm", showItemDetailForm);
		return "redirect:/Admin/ShowItemDetail";
	}
}
