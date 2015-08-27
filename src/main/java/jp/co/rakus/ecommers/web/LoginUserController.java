package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.service.LoginUserService;

/**
 * ユーザのログインをするControllerクラス
 * 
 * @author rksuser
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/loginUser")
@SessionAttributes("user")
public class LoginUserController {
	@Autowired
	private LoginUserService loginUserService;

	@ModelAttribute
	private LoginUserForm setUpForm() {
		return new LoginUserForm();
	}

	@RequestMapping(value = "/login")
	public String login(@Validated LoginUserForm form, BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			return index();
		}

		UserPage user = loginUserService.execute(form, result, model);

		if (user == null) {
			return index();
		}

		model.addAttribute("user", user);
		redirectAttributes.addFlashAttribute("user", user);
		return "redirect:/serchItem/";
	}

	@RequestMapping
	public String index() {
		return "loginUser";
	}

}
