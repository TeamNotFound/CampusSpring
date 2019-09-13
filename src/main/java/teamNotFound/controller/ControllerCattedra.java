package teamNotFound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.CattedraDao;
import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.model.Cattedra;
@Controller
public class ControllerCattedra {

	@Autowired 
	CattedraDao cattedradao;
	@Autowired
	ProfessoreDao professoreDao;
	@Autowired
	CorsoDao corsoDao;
	@Autowired
	FacoltaDao facoltaDao;
	
	@RequestMapping(value="/inserimentoCattedra", method=RequestMethod.GET)
	public  String insCatt(HttpServletRequest request, ModelMap model) {
		model.addAttribute("cattedra", new Cattedra());
		model.addAttribute("professori",professoreDao.getAll() );
		model.addAttribute("facolta", facoltaDao.getAll());
		model.addAttribute("corsi", corsoDao.getAll());
		return "cattedra/cattedraForm";
		}
	
	@RequestMapping(value="/inserimentoCattedra", method=RequestMethod.POST)
	public String insCattPost(@Valid Cattedra cattedra, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			System.out.println("Errore"); 
			model.addAttribute("cattedra", new Cattedra());
	     return "redirect:/inserimentoCattedra";
	
		}else {
			cattedradao.inserimento(cattedra);
			return "redirect:/";
		}
	}
	@RequestMapping(value="/rimuoviCattedra", method=RequestMethod.GET)
	public  String rimCatt(HttpServletRequest request, ModelMap model) {
		model.addAttribute("cattedra", new Cattedra());
		return "cattedra/rimozioneCattedra";
		}
	@RequestMapping(value="/rimuoviCattedra/{composedId}", method=RequestMethod.POST)
	public String rimCattPost( @PathVariable String composedId, @RequestParam Integer corsoId, @RequestParam Integer facoltaId, @RequestParam Integer profId) {
		
		Cattedra cattedra = cattedradao.getByComposedId(corsoId,profId,facoltaId);
		cattedradao.remove(cattedra);
		return "redirect:/";
	}
	
	
}

