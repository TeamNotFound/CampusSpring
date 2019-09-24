package teamNotFound.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.model.Account;
import teamNotFound.model.DataAppello;
import teamNotFound.model.Professore;
import teamNotFound.model.Utente;

@Controller
public class ControllerDataAppello {
		
		@Autowired
		private DataAppelloDao dataAppelloDao;
		@Autowired
		private ProfessoreDao professoreDao;
		@Autowired
		private FacoltaDao facoltaDao;
		@Autowired
		private CorsoDao corsoDao;
		@Autowired
		private AccountDao accountDao;
		@Autowired
		private SmartValidator validator;

		@GetMapping("/AppelloInserimento")
		public String InserisciAppello(Model model, Principal principal) {
			model.addAttribute("dataAppello", new DataAppello());
			Account a = accountDao.getByUsername(principal.getName());
			Utente u =a.getUtente();
			model.addAttribute("account", a);
			model.addAttribute("cattedre", professoreDao.getByIdWithCorsi(u.getId()).getCattedra());
			return "/dataAppello/dataAppelloForm";
		}
		
		
		@PostMapping(value="/AppelloInserimento")
		public String InserimentoAppello(@RequestParam("dataAppello") String date, Principal principal, @RequestParam("cattedra") String id) {
			DataAppello dataAppello = new DataAppello();
			
			// Inserisci professore
			Account a = accountDao.getByUsername(principal.getName());
			Professore pf = professoreDao.getById(a.getUtente().getId());
			dataAppello.setProfessore(pf);
			
			// Inserisci data appello
			Date dataDaInserire = null;
			try {
				dataDaInserire = new SimpleDateFormat("yy-MM-dd").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dataAppello.setDataAppello(dataDaInserire);

			// Inserisci facolta e corso
			String[] values=id.split("-");
			dataAppello.setFacolta(facoltaDao.getById(Integer.parseInt(values[0])));
			dataAppello.setCorso(corsoDao.getById(Integer.parseInt(values[1])));

			// validazione
			
			BindingResult result = new BeanPropertyBindingResult(dataDaInserire, "dataDaInserire");
			validator.validate(dataDaInserire, result);
			
			if(!result.hasErrors()) {
				dataAppelloDao.inserimento(dataAppello);

				return "redirect:/";
			}else{
				return "/dataAppello/dataAppelloForm";
			}
			
		}
	}
