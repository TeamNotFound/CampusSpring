package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Professore;

<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/nykomer/CampusSpring
@Repository
public class ProfessoreDao extends CrudGenerico<Professore, Integer> implements CRUDInterface<Professore , Integer>{
	
	public ProfessoreDao () {
		this.classeT=Professore.class;
	}
	public Professore getByIdWithCorsi(int id) {
<<<<<<< HEAD
		
		Professore professore = entity.find(Professore.class, id);
		professore.getProfessoriCorsi().size();
=======
		Professore professore = entity.find(Professore.class, id);
		professore.getCattedra().size();
>>>>>>> branch 'master' of https://github.com/nykomer/CampusSpring
		return professore;
	}

}


