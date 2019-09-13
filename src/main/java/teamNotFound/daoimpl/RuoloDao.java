package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Ruolo;
@Repository
public class RuoloDao extends CrudGenerico<Ruolo, Integer> {

	@SuppressWarnings("unchecked")
	
	public RuoloDao() {
		this.classeT=Ruolo.class;
	}

	}
