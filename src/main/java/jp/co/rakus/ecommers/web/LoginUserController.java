package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.LoginUserService;

/**
 * ユーザのログインをするControllerクラス
 * @author rksuser
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/loginUser")
public class LoginUserController {

private LoginUserService loginUserService;

<<<<<<< HEAD
@ModelAttribute
private LoginUserForm setUpForm(){
	return new LoginUserForm();
}
=======
	@RequestMapping
	public String index() {
		return "loginUser";
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
>>>>>>> 592accf6a1182a7851789b4284cc0302712a42e3

@RequestMapping
public String index(){
	return "loginUser";
}
@RequestMapping(value = "login")
public String login(@Validated LoginUserForm form, BindingResult result, Model model){
	if(result.hasErrors()){
		return index();
	}
//	UserPage userPage = loginUserService.login(form);
//	model.addAttribute("userPage", userPage);
	
//	if()
	
	return "/serchItem/";
}
}
