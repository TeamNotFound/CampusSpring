package teamNotFound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.model.Account;
import teamNotFound.model.Professore;

@Controller
public class ProfessoreController {

	@Autowired
	private ProfessoreDao professoreDao;

	@GetMapping("/GestioneProfessori")
	public String gestioneProfessori(Model model) {
		model.addAttribute("professori", professoreDao.getAll());
		return "professore/gestioneProfessori";
	}

	@GetMapping("/InserimentoProfessore")
	public String newProfessore(HttpServletRequest request, Model model) {
		if((boolean) request.getSession().getAttribute("rettore")) {
			model.addAttribute("professore", new Professore());

			return "professore/profForm";
		} else {
			return "redirect:/Login";
		}
	}

	@PostMapping("/InserimentoProfessore")
	public String saveProfessore(@Valid Professore professore, BindingResult result) {
		if(result.hasErrors()) {
			return "professore/profForm";
		}else {
			professoreDao.inserimento(professore);
			return "redirect:/GestioneProfessori";
		}
	}
	
	@GetMapping("/Professore/Promuovi/{id}")
	public String promuoviProfessore(@PathVariable Integer id, HttpServletRequest request) {
		Account a = (Account) request.getSession().getAttribute("account");
		Professore rettore = (Professore)a.getUtente();
		
		Professore professore=professoreDao.getById(id);
		
		rettore.setRettore(false);
		professore.setRettore(true);
		
		professoreDao.update(rettore);
		professoreDao.update(professore);
		
		request.getSession().invalidate();
		
		return "redirect:/Login";
	}
	
	@GetMapping("/Professore/Rimuovi/{id}")
	public String eliminaProfessore(@PathVariable Integer id) {
		professoreDao.remove(professoreDao.getById(id));
		
		return "redirect:/GestioneProfessori";
	}
}
