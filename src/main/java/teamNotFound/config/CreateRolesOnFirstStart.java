package teamNotFound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import teamNotFound.daoimpl.RuoloDao;
import teamNotFound.model.Ruolo;

@Component
public class CreateRolesOnFirstStart {

	@Autowired
	private RuoloDao ruoloDao;
	
	@EventListener(classes = {ContextRefreshedEvent.class})
	public void createRolesAtStart() {
		Ruolo rettore = ruoloDao.getByName("RETTORE");
		Ruolo professore = ruoloDao.getByName("PROFESSORE");
		Ruolo studente = ruoloDao.getByName("STUDENTE");
		
		if(rettore == null) {
			ruoloDao.inserimento(new Ruolo("RETTORE"));
		}
		
		if(professore == null) {
			ruoloDao.inserimento(new Ruolo("PROFESSORE"));
		}
		
		if(studente == null) {
			ruoloDao.inserimento(new Ruolo("STUDENTE"));
		}
	}
}
