package teamNotFound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.model.Account;

@Controller
public class CambiaPasswordController {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private BCryptPasswordEncoder cript;
	
	@GetMapping("/CambiaPassword")
	public String returnPage() {
		return "cambiaPassword";
	}
	
	@PostMapping("/CambiaPassword")	
	public String cambia(@RequestParam("newPassword") String password, @RequestParam("oldPassword") String oldPassword) {
		
		Account account= accountDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("utente= "+account.getUsername());
		
		if(cript.matches(oldPassword,account.getPassword())) {
			account.setPassword(cript.encode(password));
			
			accountDao.update(account);
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
			
			return "redirect:/login";
		} else {
			System.out.println("siamo nell'else");
			return "cambiaPassword";
		}
	}

}
