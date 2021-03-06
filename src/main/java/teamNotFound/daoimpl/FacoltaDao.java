package teamNotFound.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Facolta;
@Repository
public class FacoltaDao extends CrudGenerico<Facolta, Integer> {

	public FacoltaDao () {
		this.classeT=Facolta.class;
	}	
	
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

	public List<Facolta> search(String facolta) {
		return entity.createQuery("select f from Facolta f where f.facolta like :facolta", Facolta.class)
					 .setParameter("facolta", "%"+facolta+"%").getResultList();
	}
}
