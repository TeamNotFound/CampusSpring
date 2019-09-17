package teamNotFound.controller;


import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.RuoloDao;
import teamNotFound.model.Cattedra;
import teamNotFound.model.Corso;
import teamNotFound.model.Facolta;
import teamNotFound.model.Ruolo;

@Controller
public class ControllerCorsoFacolta {

	@Autowired
	RuoloDao ruoloDao;
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
		model.addAttribute("newRuolo", new Ruolo());

		return "corsiFacolta/corsoFacoltaForm";
	}


	@RequestMapping(value="/inserimentoCorso", method=RequestMethod.POST)
	public String insCorPost(@Valid Corso corso, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsiFacoltaForm";

		}else {
			corsoDao.inserimento(corso);
			return "redirect:/CorsoFacolta";
		}
	}


	@RequestMapping(value="/rimuoviCorso/{composedId}", method=RequestMethod.POST)
	public String rimCorPost( @RequestParam Integer corsoId, @RequestParam Integer facoltaId, @RequestParam Integer profId) {

		Corso corso = corsoDao.getById(corsoId);
		corsoDao.remove(corso);
		return "redirect:/";
	}

	@RequestMapping(value="/inserimentoFacolta", method=RequestMethod.POST)
	public String insFacPost(@Valid Facolta facolta, BindingResult result, ModelMap model) {

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

	@PostMapping("corso-facolta")
	public String addCorsoFacolta (@RequestParam Integer corsoId, @RequestParam Integer facoltaId) {
		Facolta facolta = facoltaDao.getByIdWithCorsi(facoltaId);
		Corso corso = corsoDao.getById(corsoId);
		facolta.addCorso(corso);
		facoltaDao.update(facolta);
		return "redirect:/Facolta/"+facoltaId;

	}

	@GetMapping("Facolta/{id}")
	public String showFacolta (@PathVariable Integer id, Model model) {

		Facolta f = facoltaDao.getByIdWithCorsiAndCattedre(id);

		HashMap<Integer, Cattedra> cattedre = new HashMap<Integer, Cattedra>();
		for(Cattedra pc : f.getCattedre()) {
			cattedre.put(pc.getCorso().getId(), pc);
		}

		model.addAttribute("cattedre", cattedre);
		model.addAttribute("facolta", f);
		model.addAttribute("corsi", corsoDao.getAll());

		return "corsiFacolta/facolta";		
	}
	
	@PostMapping("/Ruolo")
	public String insRuolo(@Valid Ruolo ruolo, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("Errore");
			return "corsiFacoltaForm";

		}else {
			ruoloDao.inserimento(ruolo);
			return "redirect:/CorsoFacolta";
		}
		
	}
}
