package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.LoginUserRepository;
import jp.co.rakus.ecommers.web.LoginUserForm;
import jp.co.rakus.ecommers.web.UserPage;

/**
 * ログイン用コントローラー
 * @author ShoKodama
 *
 */
@Service
public class LoginUserService {

@Autowired
private LoginUserRepository loginUserRepository;

/**
 * ログイン
 * @param form
 * @param result
 * @param model
 * @return
 */
public UserPage execute(LoginUserForm form,
		BindingResult result,
		Model model){
	String email = form.getEmail();
	
	User user = loginUserRepository.findbyMailAddress(email);
	if(user == null){
		return null;
	}else{
	UserPage page = new UserPage();
	
	page.setId(user.getId());
	page.setName(user.getName());
	page.setEmail(user.getEmail());
	page.setAddress(user.getAddress());
	page.setTelephone(user.getTelephone());
	
	return page;
	}
}	

}
