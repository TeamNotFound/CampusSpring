package teamNotFound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import teamNotFound.config.BCryptUtil;
import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.model.Account;
import teamNotFound.model.Professore;
import teamNotFound.model.Studente;




@Controller
public class HomeController {
	@Autowired
	private ProfessoreDao professoreDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired 
	private BCryptUtil bCryptUtil;

	@RequestMapping(value="/")
	public String index(HttpServletRequest request,ModelMap model) {
		if(professoreDao.getAll().isEmpty()) {
			model.addAttribute("professore", new Professore());
			return "firstAccess";
		}
		else {
			return "index";
		}
	}

	@RequestMapping(value="/FirstAccess", method= RequestMethod.POST)
	public String firstAccess(@Valid Professore professore, BindingResult result, HttpServletRequest request,ModelMap model) {
		if(result.hasErrors()) {
			return "firstAccess";
		}else {
			professoreDao.inserimento(professore);
			return "index";
		}
	}
	
	@RequestMapping(value="/Login")
	public String login(HttpServletRequest request,ModelMap model) {
		model.addAttribute("account",new Account());
		return "session/login";
	}

	@RequestMapping(value="/Login", method= RequestMethod.POST)
	public String loginForm(@Valid Account account, BindingResult result, HttpServletRequest request,ModelMap model) {
		Account a=accountDao.getByUsername(account.getUsername());
		if(a != null && bCryptUtil.checkPs2(account.getPassword(), a.getPassword())){
			System.out.println("Login successfull.");

			HttpSession session = request.getSession(true);

			session.setAttribute("account", a);

			if(a.getUtente() instanceof Studente){
				session.setAttribute("studente", true);
				session.setAttribute("rettore", false);

			} else if (a.getUtente() instanceof Professore) {
				session.setAttribute("studente", false);

				Professore p = (Professore) a.getUtente();
				System.out.println(p.isRettore());

				if(p.isRettore()) {
					session.setAttribute("rettore", true);
				} else {
					session.setAttribute("rettore", false);

				}
			}
			return "index";
		}else {
			return "session/login";
		}
	}
}

