package teamNotFound.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.daoimpl.RuoloDao;
import teamNotFound.model.Account;
import teamNotFound.model.Professore;
import teamNotFound.model.Ruolo;

@Controller
public class ProfessoreController {

	@Autowired
	private ProfessoreDao professoreDao;
	@Autowired
	private BCryptPasswordEncoder cript;
	@Autowired
	private RuoloDao ruoloDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private SmartValidator validator;

	@GetMapping("/GestioneProfessori")
	public String gestioneProfessori(Model model) {
		model.addAttribute("professori", professoreDao.getAll());
		return "professore/gestioneProfessori";
	}

	@GetMapping("/InserimentoProfessore")
	public String newProfessore(Model model, Principal principal) {
			model.addAttribute("professore", new Professore());
			return "professore/profForm";
	}

	@PostMapping("/InserimentoProfessore")
	public String saveProfessore(Professore professore, BindingResult result,
									@Value("${profile.pic.default}") String defaultPic) {
		
		professore.setImageGeneratedName(defaultPic);
		validator.validate(professore, result);
		
		if(result.hasErrors()) {
			return "professore/profForm";
		}else {
			professore.getAccount().setPassword(cript.encode(professore.getAccount().getPassword()));
			
			Ruolo ruolo = ruoloDao.getByName("PROFESSORE");
			professore.getAccount().setRuolo(ruolo);
			
			professoreDao.inserimento(professore);
			return "redirect:/GestioneProfessori";
		}
	}
	
	@GetMapping("/Professore/Promuovi/{id}")
	public String promuoviProfessore(@PathVariable Integer id, Principal principal) {
		
		Ruolo rett = ruoloDao.getById(1);
		Ruolo prof = ruoloDao.getById(2);
		
		Account a = accountDao.getById(professoreDao.getById(id).getAccount().getId());
		a.setRuolo(rett);
		accountDao.update(a);
		
		Account rettore = accountDao.getByUsername(principal.getName());
		rettore.setRuolo(prof);
		accountDao.update(rettore);
		
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		
		return "redirect:/login";
	}
	
	@GetMapping("/Professore/Rimuovi/{id}")
	public String eliminaProfessore(@PathVariable("id") Integer id) {
		professoreDao.remove(professoreDao.getById(id));
		
		return "redirect:/GestioneProfessori";
	}
}
