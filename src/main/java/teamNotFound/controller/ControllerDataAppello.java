package teamNotFound.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import teamNotFound.daoimpl.CorsoDao;
import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.model.Account;
import teamNotFound.model.Corso;
import teamNotFound.model.DataAppello;
import teamNotFound.model.Facolta;
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

		@GetMapping("/AppelloInserimento")
		public String InserisciAppello(HttpServletRequest request, Model model) {
			model.addAttribute("dataAppello", new DataAppello());
			Utente u = ((Account) request.getSession().getAttribute("account")).getUtente();
			model.addAttribute("cattedre", professoreDao.getByIdWithCorsi(u.getId()).getCattedra());
			return "/dataAppello/dataAppelloForm";
		}
		
		@PostMapping(value="/AppelloInserimento")
		public String InserimentoAppello(@PathVariable String id, HttpServletRequest request) {
			DataAppello dataAppello = new DataAppello();
			String[] values=id.split("-");
			Professore pf = (Professore) ((Account) request.getSession().getAttribute("account")).getUtente();
			
			String param = request.getParameter("data");
			Date dataDaInserire = null;
			try {
				dataDaInserire = new SimpleDateFormat("yy-MM-dd").parse(param);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dataAppello.setFacolta(facoltaDao.getById(Integer.parseInt(values[0])));
			dataAppello.setCorso(corsoDao.getById(Integer.parseInt(values[1])));
			
			if(dataDaInserire != null) {
				dataAppello.setDataAppello(dataDaInserire);
				dataAppelloDao.inserimento(dataAppello);
				String uri=request.getContextPath();
				return "redirect:"+(uri+"/Home");
			}else
			{
				return "redirect:/AppelloInserimento";
			}
			
		}
	}
