package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;


import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Professore;


@Repository
public class ProfessoreDao extends CrudGenerico<Professore, Integer>{
	
	public ProfessoreDao () {
		this.classeT=Professore.class;
	}
	public Professore getByIdWithCorsi(int id) {
		
		Professore professore = entity.find(Professore.class, id);
		professore.getCattedra().size();
		return professore;
	}
}


