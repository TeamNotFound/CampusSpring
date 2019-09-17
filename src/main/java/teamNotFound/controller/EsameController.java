package teamNotFound.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.daoimpl.EsameDao;
import teamNotFound.daoimpl.PrenotazioneDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.daoimpl.StudenteDao;
import teamNotFound.model.Account;
import teamNotFound.model.DataAppello;
import teamNotFound.model.Esame;
import teamNotFound.model.Professore;
import teamNotFound.model.Studente;

@Controller 
public class EsameController {

	@Autowired
	private EsameDao esameDao;
	@Autowired
	private DataAppelloDao dataDao;
	@Autowired
	private StudenteDao studenteDao;
	@Autowired
	private PrenotazioneDao prenotazioneDao;
	@Autowired
	private ProfessoreDao professoreDao;
	
	@GetMapping("/Esami/Data/{id}")
	public String newEsame(@PathVariable Integer id,Model model) {
		DataAppello data = dataDao.getByIdWithPrenotazioni(id);
		model.addAttribute("prenotazioni",data.getPrenotazioni());
		return "esame/convalidaEsami";
	}
	
	@PostMapping("/Esami/Data/{id}")
	public String convalidaEsame(@PathVariable Integer id,HttpServletRequest request,Esame esame) {
		DataAppello data = dataDao.getById(id);
		
		int id_studente = Integer.parseInt(request.getParameter("studente"));
		Studente studente = studenteDao.getById(id_studente);
		
		esame.setCorso(data.getCorso());
		esame.setFacolta(data.getFacolta());
		esame.setProfessore(data.getProfessore());
		esame.setStudente(studente);
		esame.setVotoEsame(Integer.parseInt(request.getParameter("votoEsame")));
		
		esameDao.inserimento(esame);

		prenotazioneDao.remove(prenotazioneDao.getByComposedId(id_studente, id));
		
		String uri=request.getRequestURI();
		
		return "redirect:"+uri;
	}
	
	@GetMapping("/Esami/Cattedre")
	public String selezionaCattedre(HttpServletRequest request,Model model) {
		Account a=(Account)request.getSession().getAttribute("account");
		Professore p = (Professore) a.getUtente();
		
		p = professoreDao.getByIdWithCorsi(p.getId());
		
		model.addAttribute("cattedre",  p.getCattedra());
		
		return "esame/selezioneCattedreEsami";
	}
	
	@GetMapping("/Esami/Cattedra/{composedId}")
	public String selezionaDataAppelloEsami(@PathVariable String composedId,Model model) {
		String[] ids=composedId.split("-");
		
		int id_professore = Integer.parseInt(ids[0]);
		int id_facolta = Integer.parseInt(ids[1]);
		int id_corso = Integer.parseInt(ids[2]);
		
		List<DataAppello> date = dataDao.getByProfessoreFacoltaAndCorso(id_professore, id_facolta, id_corso);
		
		model.addAttribute("date", date);
		
		return "esame/selezioneDataAppelloEsami";
	}
	
	@GetMapping("/Esami/Visualizza")
	public String visualizzaEsami(HttpServletRequest request, Model model) {
		Account a = (Account) request.getSession().getAttribute("account");
		Studente s = (Studente) a.getUtente();
		
		s = studenteDao.getByIdWithEsami(s.getId());
		
		model.addAttribute("esami", s.getEsami());
		
		return "esame/visualizzaEsami";
	}
}
