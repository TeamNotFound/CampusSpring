package teamNotFound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class ErrorController {

	@GetMapping("/access-denied")
	public String error() {
		System.out.println("Error happened");
		return "error/access-denied";
	}
	
	@ExceptionHandler(MultipartException.class)
	public ModelAndView handleError(MultipartException exception) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("error", exception.getMessage());
	    modelAndView.setViewName("profilePicForm");
	    return modelAndView;
	  }
	}
