package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザのログインをするControllerクラス
 * @author rksuser
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/loginUser")
public class LoginUserController {

}
