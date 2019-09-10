package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Corso;

@Repository
public class CorsoDao extends CrudGenerico<Corso, Integer> implements CRUDInterface<Corso , Integer>{

	public CorsoDao () {
		this.classeT=Corso.class;
	}
	
	public Corso getByIdWithFacolta(int id) {
		Corso corso=entity.find(Corso.class, id);
		corso.getFacolta().size();
		return corso;
		}
}
