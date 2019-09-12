package teamNotFound.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.model.Corso;
import teamNotFound.model.Facolta;

@Controller
public class ControllerCorsoFacolta {

	@Autowired
	CorsoDao corsoDao;
	@Autowired
	FacoltaDao facoltaDao;

	@RequestMapping(value="/CorsoFacolta", method=RequestMethod.GET)
	public  String insCorFac(ModelMap model) {
		model.addAttribute("facolta", facoltaDao.getAll());
		model.addAttribute("corsi", corsoDao.getAll());
		model.addAttribute("newFacolta", new Facolta());
		model.addAttribute("newCorso", new Corso());

		return "corsiFacolta/corsoFacoltaForm";
	}


	@RequestMapping(value="/inserimentoCorso", method=RequestMethod.POST)
	public String insCorPost(@Valid Corso corso, BindingResult result, ModelMap model) {
		model.addAttribute("newCorso", new Corso());

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsiFacoltaForm";

		}else {
			corsoDao.inserimento(corso);
			return "redirect:/CorsoFacolta";
		}
	}


	@RequestMapping(value="/rimuoviCorso/{composedId}", method=RequestMethod.POST)
	public String rimCorPost( @PathVariable String composedId, @RequestParam Integer corsoId, @RequestParam Integer facoltaId, @RequestParam Integer profId) {

		Corso corso = corsoDao.getById(corsoId);
		corsoDao.remove(corso);
		return "redirect:/";
	}

	@RequestMapping(value="/inserimentoFacolta", method=RequestMethod.POST)
	public String insFacPost(@Valid Facolta facolta, BindingResult result, ModelMap model) {
		model.addAttribute("newFacolta", new Facolta());

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsiFacolta/corsoFacoltaForm";
		}else {
			facoltaDao.inserimento(facolta);
			return "redirect:/";
		}
	}


	@RequestMapping(value="/rimuoviFacolta/{composedId}", method=RequestMethod.POST)
	public String rimFacPost( @PathVariable String composedId, @RequestParam Integer facoltaId) {

		Facolta facolta = facoltaDao.getById(facoltaId);
		facoltaDao.remove(facolta);
		return "redirect:/";
	}
	@RequestMapping(value="/visualizza", method=RequestMethod.POST)
	public String visualizzaF(@Valid Corso corso, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsiFacolta/corsoFacolta";

		}else {
			facoltaDao.getAll();
			return "redirect:/corsiFacoltaForm";
		}
	}
}
