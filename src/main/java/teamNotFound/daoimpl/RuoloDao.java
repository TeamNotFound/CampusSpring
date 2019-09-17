package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Ruolo;
@Repository
public class RuoloDao extends CrudGenerico<Ruolo, Integer> {
	
	public RuoloDao() {
		this.classeT=Ruolo.class;
	}

	public Ruolo getByName(String nome) {
		try {
			return entity.createQuery("select a from Ruolo a where a.ruolo = :ruolo" , Ruolo.class)
					.setParameter("ruolo", nome).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
