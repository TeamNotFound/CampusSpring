package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Studente;

<<<<<<< HEAD
import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Studente;

=======
@Repository
>>>>>>> branch 'master' of https://github.com/nykomer/CampusSpring
public class StudenteDao extends CrudGenerico<Studente, Integer> implements CRUDInterface<Studente , Integer>{
 


	public StudenteDao () {
this.classeT=Studente.class;	}
	
	
	public Studente getByIdWithPrenotazioni(int id) {
<<<<<<< HEAD
		Studente studente =entity.find(Studente.class, id);
=======
		Studente studente = entity.find(Studente.class, id);
>>>>>>> branch 'master' of https://github.com/nykomer/CampusSpring
		studente.getPrenotazioni().size();
		return studente;
	}
	
	public Studente getByIdWithEsami(int id) {
		Studente studente = entity.find(Studente.class, id);
		studente.getEsami().size();
		return studente;
	}
	
	public Studente getByIdWithPrenotazioniEsami(int id) {
		Studente studente = entity.find(Studente.class, id);
		studente.getPrenotazioni().size();
		studente.getEsami().size();
		return studente;
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/nykomer/CampusSpring
	}

	
}