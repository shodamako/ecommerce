package jp.co.rakus.ecommers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.SessionAttributes;

@EnableAutoConfiguration
@ComponentScan
@SessionAttributes("orderItemList")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
//		Model model = new RedirectAttributesModelMap();
//		ArrayList<OrderItem> orderItemList = null;
//		model.addAttribute("orderItemList", orderItemList);
	}
}
