package teamNotFound.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.daoimpl.PrenotazioneDao;
import teamNotFound.daoimpl.StudenteDao;
import teamNotFound.model.Account;
import teamNotFound.model.Corso;
import teamNotFound.model.DataAppello;
import teamNotFound.model.Esame;
import teamNotFound.model.Facolta;
import teamNotFound.model.Prenotazione;
import teamNotFound.model.Studente;

@Controller
public class PrenotazioneController {

	@Autowired
	private PrenotazioneDao prenotazioneDao;
	@Autowired
	private DataAppelloDao dataAppelloDao;
	@Autowired
	private FacoltaDao facoltaDao;
	@Autowired
	private StudenteDao studenteDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private SmartValidator validator;


	@GetMapping("/Prenotazione/Corso/{id}")
	public String prenotazioneCorso(@PathVariable Integer id, Model model, Principal principal) {
		Account account = accountDao.getByUsername(principal.getName());
		int idFacolta = ((Studente) account.getUtente()).getFacolta().getId();

		List<DataAppello> date = dataAppelloDao.getByFacoltaAndCorso(idFacolta, id);

		model.addAttribute("date", date);

		return "prenotazione/corsoPrenotazioni";
	}

	@GetMapping("/PrenotazioneDelete")
	public String prenotazioneDelete(@PathVariable Integer id) {
		Prenotazione p=prenotazioneDao.getById(id);
		prenotazioneDao.remove(p);

		return "redirect:/InserisciPrenotazione";
	}

	@GetMapping("/Prenotazione/PrenotazioneEsame/{id}")
	public String prenotazioneData(@PathVariable Integer id,Model model) {
		DataAppello dataAppello = new DataAppello();

		dataAppello = dataAppelloDao.getById(id);

		model.addAttribute("dataPrenotata", dataAppello);

		return "prenotazione/prenotazioneEsame";
	}

	@PostMapping("/Prenotazione/PrenotazioneEsame/{id}")
	public String prenotazioneEsame(Principal principal, @RequestParam("dataPrenotaId") Integer dataPrenotaId) {
		Prenotazione prenotazione = new Prenotazione();

		Account account = accountDao.getByUsername(principal.getName());
		prenotazione.setStudente((Studente) account.getUtente());

		DataAppello dataAppello= dataAppelloDao.getById(dataPrenotaId);
		prenotazione.setDataAppello(dataAppello);

		Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		prenotazione.setDataPrenotazione(date);

		prenotazioneDao.inserimento(prenotazione);

		return "redirect:/PrenotazioneVisualizza";
	}

	private List<Corso> getCorsiDaRimuovere(Studente studente){
		List<Corso> corsiDaRimuovere = new ArrayList<Corso>();

		for(Esame esame : studente.getEsami()) {
			corsiDaRimuovere.add(esame.getCorso());
		}

		for(Prenotazione prenotazione : studente.getPrenotazioni()) {
			corsiDaRimuovere.add(prenotazione.getDataAppello().getCorso());
		}

		return corsiDaRimuovere;
	}

	@GetMapping("/Prenotazione")
	public String prenotazioneFacolta(Model model, Principal principal) {
		Account account = accountDao.getByUsername(principal.getName());
		Studente studente = (Studente) account.getUtente();
		
		studente = studenteDao.getByIdWithPrenotazioniEsami(studente.getId());

		Facolta facolta = facoltaDao.getByIdWithCorsi(studente.getFacolta().getId());

		List<Corso> corsiDaRimuovere = getCorsiDaRimuovere(studente);

		for(Corso corso : corsiDaRimuovere) {
			facolta.getCorsi().remove(corso);
		}
		model.addAttribute("facolta", facolta);

		return "prenotazione/prenotazioniForm";
	}

	@PostMapping("/Prenotazione")
	public String prenotazioneInserimento(Principal principal, @RequestParam("dataPrenotaId") Integer dataAppelloId) {
		Prenotazione prenotazione = new Prenotazione();
		Date d = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

		prenotazione.setDataAppello(dataAppelloDao.getById(dataAppelloId));
		prenotazione.setDataPrenotazione(d);

		Account account = accountDao.getByUsername(principal.getName());
		prenotazione.setStudente((Studente) account.getUtente());

		BindingResult result = new BeanPropertyBindingResult(prenotazione, "prenotazione");
		validator.validate(prenotazione, result);
		
		if(!result.hasErrors()) {
			prenotazioneDao.inserimento(prenotazione);
			return "redirect:/PrenotazioneVisualizza";
		}else{
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/PrenotazioneVisualizza")
	public String prenotazioneVisualizza(Model model, Principal principal) {
		Account s =accountDao.getByUsername(principal.getName());

		Studente st = studenteDao.getByIdWithPrenotazioni(s.getUtente().getId());
		
		model.addAttribute("prenotazioni",st.getPrenotazioni());
		
		return "prenotazione/prenotazioni";
	}
}
