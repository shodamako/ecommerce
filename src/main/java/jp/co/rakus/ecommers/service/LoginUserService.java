package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.LoginUserRepository;
import jp.co.rakus.ecommers.web.LoginUserForm;

@Service
public class LoginUserService {
@Autowired
private LoginUserRepository loginUserRepository;

//public UserPage login(LoginUserForm form){
//	User user = loginUserRepository.findbyMailAddress(form.getMailAddress());
//	if(form.getPassword().equals(user.getPassword())){
//		UserPage userPage = new UserPage();
//		userPage.setId(user.getId());
//		userPage.setName(user.getName());
//		userPage.setEmail(user.getEmail());
//		userPage.setAddress(user.getAddress());
//		userPage.setTelepone(user.getTelephone());
//	return userPage;	
//	}
//	return null;
//}
}
