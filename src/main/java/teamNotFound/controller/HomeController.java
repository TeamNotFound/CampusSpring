package teamNotFound.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.config.BCryptUtil;
import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.daoimpl.StudenteDao;
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
	@Autowired
	private FacoltaDao facoltaDao;
	@Autowired
	private StudenteDao studenteDao;
	@Autowired
	private SmartValidator validator;
	
	
	
	@RequestMapping(value= {"/","/Home"})
	public String index(HttpServletRequest request,ModelMap model) {
		if(professoreDao.getAll().isEmpty()) {
			model.addAttribute("professore", new Professore());
			return "redirect:/FirstAccess";
		}
		else {
			return "index";
		}
	}

	@RequestMapping(value="/FirstAccess", method=RequestMethod.GET)
	public String firstAccessForm(Model model) {
		model.addAttribute("professore", new Professore());
		return "firstAccess";
	}
	@RequestMapping(value="/FirstAccess", method= RequestMethod.POST)
	public String firstaccess(@Valid Professore professore, BindingResult result) {
		System.out.println("nel do post");
		System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return "firstAccess";
		}else {
			professore.getAccount().setPassword(bCryptUtil.hashPsw(professore.getAccount().getPassword()));
			professoreDao.inserimento(professore);
			return "redirect:/Login";
		}
	}
	
	@RequestMapping(value="/Login")
	public String login(HttpServletRequest request,ModelMap model) {
		model.addAttribute("logAccount",new Account());
		System.out.println("siamo in login get");
		return "session/login";
	}

	@RequestMapping(value="/Login", method= RequestMethod.POST)
	public String loginForm(@ModelAttribute("logAccount") Account account, HttpServletRequest request,ModelMap model) {
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
			return "redirect:/";
		}else {
			model.addAttribute("account", new Account());
			return "redirect:/Login?error";
		}
	}
	
	@GetMapping("/Studente")
	public String inserimentoFacolta(Model model) {
		model.addAttribute("facolta", facoltaDao.getAll());
		model.addAttribute("studente", new Studente());
		return "studenteForm";
	}

	@PostMapping("/Studente")
	public String inserimentoStudente(Studente studente, BindingResult result,Model model, @RequestParam("selFacolta") Integer facoltaId) {
		
		studente.setFacolta(facoltaDao.getById(facoltaId));
		validator.validate(studente, result);
		
		if(result.hasErrors()) {
			model.addAttribute("facolta", facoltaDao.getAll());
			return "studenteForm";
		}else {
			studente.getAccount().setPassword(bCryptUtil.hashPsw(studente.getAccount().getPassword()));
			studenteDao.inserimento(studente);
			return "redirect:/Login";
		}
	}
	@RequestMapping(value="/Logout", method= RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/Login";
	}
}

