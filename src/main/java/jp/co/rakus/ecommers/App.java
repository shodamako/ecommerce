package jp.co.rakus.ecommers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.SessionAttributes;

@SpringBootApplication
@SessionAttributes("orderItemList")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
//		Model model = new RedirectAttributesModelMap();
//		ArrayList<OrderItem> orderItemList = null;
//		model.addAttribute("orderItemList", orderItemList);
	}
}
