package jp.co.rakus.ecommers.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.RegisterUserRepository;
import jp.co.rakus.ecommers.web.RegisterUserForm;
import jp.co.rakus.ecommers.web.UserPage;

/**
 * ユーザー登録サービスクラス
 * @author ShoKodama
 *
 */
@Service
public class RegisterUserService {
	
	@Autowired
	RegisterUserRepository registerUserRepository;
	
	/**
	 * 新規ユーザーのアドレス重複チェック
	 * @param email　新規ユーザー記入のアドレス
	 * @return　重複アドレスのユーザー情報、無い場合はnullが返る
	 */
	public User findByEmail(String email){
		return registerUserRepository.findByEmail(email);
	}
	
	/**
	 * 新規ユーザー情報登録
	 * @param user　新規ユーザー情報
	 * @return　新規ユーザー情報
	 */
	public UserPage execute(@Validated RegisterUserForm form,
			BindingResult result,
			Model model) {
			
			if(result.hasErrors()){
				UserPage page = null;
				return page;
			}

				User user = new User();
				BeanUtils.copyProperties(form, user);
				
				User userForPage = registerUserRepository.save(user);
				
				UserPage page = new UserPage();
				
				page.setId(userForPage.getId());
				page.setName(userForPage.getName());
				page.setEmail(userForPage.getEmail());
				page.setAddress(userForPage.getAddress());
				page.setTelephone(userForPage.getTelephone());
				
				return page;
	}
	
}
