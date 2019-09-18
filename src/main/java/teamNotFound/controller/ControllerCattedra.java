package teamNotFound.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
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
	@Autowired
	private SmartValidator validator;
	
	@RequestMapping(value="/inserimentoCattedra", method=RequestMethod.GET)
	public  String insCatt(ModelMap model) {
 		
		model.addAttribute("professori",professoreDao.getAll() );
		model.addAttribute("facolta", facoltaDao.getAll());
		model.addAttribute("corsi", corsoDao.getAll());
		return "cattedra/cattedraForm";
	}
	
	@RequestMapping(value="/inserimentoCattedra", method=RequestMethod.POST)
	public String insCattPost(Model model, @RequestParam("professore") Integer professoreId,
																 @RequestParam("facolta") Integer facoltaId,
																 @RequestParam("corso") Integer corsoId) {
				
		Cattedra cattedra = new Cattedra();
		BindingResult result = new BeanPropertyBindingResult(cattedra, "cattedra");

		cattedra.setFacolta(facoltaDao.getById(facoltaId));
		cattedra.setCorso(corsoDao.getById(corsoId));
		cattedra.setProfessore(professoreDao.getById(professoreId));
		
		
		validator.validate(cattedra, result);
		System.out.println(result.getAllErrors());
		
		if (result.hasErrors()) {
			System.out.println("Errore"); 
	     return "cattedra/cattedraForm";
	
		}else {
			cattedradao.inserimento(cattedra);
			return "redirect:/inserimentoCattedra";
		}
	}
	
	@RequestMapping(value="/rimuoviCattedra/{composedId}", method=RequestMethod.GET)
	public  String rimCatt(@PathVariable String composedId, ModelMap model) {
		
		String ids[] = composedId.split("-");
		System.out.println(ids[0]);
		System.out.println(ids[1]);
		System.out.println(ids[2]);
		Cattedra cattedra = cattedradao.getByComposedId(Integer.parseInt(ids[0]),
														Integer.parseInt(ids[1]), 
														Integer.parseInt(ids[2]));
		model.addAttribute("cattedra",cattedra);
		return "cattedra/rimozioneCattedra";
	}
	
	@RequestMapping(value="/rimuoviCattedra/{composedId}", method=RequestMethod.POST)
	public String rimCattPost(@PathVariable String composedId) {
		
		String ids[] = composedId.split("-");
		Cattedra cattedra = cattedradao.getByComposedId(Integer.parseInt(ids[0]),
														Integer.parseInt(ids[1]), 
														Integer.parseInt(ids[2]));
		cattedradao.remove(cattedra);
		return "redirect:/";
	}
	
	
}

