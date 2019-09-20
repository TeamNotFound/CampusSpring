package teamNotFound.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.model.Corso;
import teamNotFound.model.Ruolo;

@Controller
public class CorsoController {
	
	@Autowired
	CorsoDao corsoDao;

	@RequestMapping(value="/Corso", method=RequestMethod.GET)
	public  String insCorFac(ModelMap model) {
		model.addAttribute("corsi", corsoDao.getAll());
		model.addAttribute("newCorso", new Corso());
		model.addAttribute("newRuolo", new Ruolo());

		return "corso/corsi";
	}
	
	@RequestMapping(value="/inserimentoCorso", method=RequestMethod.POST)
	public String insCorPost(@Valid Corso corso, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsi";

		}else {
			corsoDao.inserimento(corso);
			return "redirect:/Corso";
		}
	}
	
	@RequestMapping(value="/rimuoviCorso/{id}", method=RequestMethod.GET)
	public String rimCorPost(@PathVariable Integer id) {

		Corso corso = corsoDao.getById(id);
		corsoDao.remove(corso);
		return "redirect:/Corso";
	}
}
