package teamNotFound.daoimpl;


import java.util.List;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Esame;


@Repository
public class EsameDao extends CrudGenerico<Esame, Integer> {
	
	public EsameDao () {
		this.classeT=Esame.class;
	}
	@SuppressWarnings("unchecked")
	public List<Esame> getByIdStudente(int idStudente){
	
		List<Esame> esami = entity.createQuery("select e from Esame e where e.studente_id= :idStudente")
				.setParameter("idStudente", idStudente).getResultList();
		return esami;
	}
	
	
}
