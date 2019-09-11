package teamNotFound.daoimpl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Studente;

@Repository
public class StudenteDao extends CrudGenerico<Studente, Integer> implements CRUDInterface<Studente , Integer>{
 
	public StudenteDao () {
		this.classeT=Studente.class;
	}
	
	
	public Studente getByIdWithPrenotazioni(int id) {
		Studente studente = entity.find(Studente.class, id);
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
	}

	
}