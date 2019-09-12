package teamNotFound.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Facolta;
@Repository
public class FacoltaDao extends CrudGenerico<Facolta, Integer> implements CRUDInterface<Facolta , Integer>{

	public FacoltaDao () {
		this.classeT=Facolta.class;
	}

	@SuppressWarnings("unchecked")
	
	
	public Facolta getByIdWithCorsi(int id) {
		
		Facolta facolta = entity.find(Facolta.class, id);
		facolta.getCorsi().size();
		return facolta;
	}
	

	public Facolta getByIdWithCorsiAndCattedre(int id) {
		
		Facolta facolta = entity.find(Facolta.class, id);
		facolta.getCorsi().size();
		facolta.getCattedre().size();
		
		return facolta;
	}
}
