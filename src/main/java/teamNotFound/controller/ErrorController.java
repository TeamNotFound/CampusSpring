package teamNotFound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/access-denied")
	public String error() {
		System.out.println("Error happened");
		return "error/access-denied";
	}
}
