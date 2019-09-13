package teamNotFound.daoimpl;



import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Cattedra;

@Repository
public class CattedraDao extends CrudGenerico<Cattedra, Integer>  {

	public CattedraDao() {
		this.classeT=Cattedra.class;
	}

	public Cattedra getByComposedId(int id_corso,int id_professore, int id_facolta) {
		try{
			Cattedra cattedra = entity.createQuery("SELECT c FROM Cattedra c where c.facolta_id = :facolta "
																	 + "c.corso_id = :corso "
																	 + "c.professore_id = :professore", Cattedra.class)
			  .setParameter("facolta", id_facolta)
			  .setParameter("corso", id_corso)
			  .setParameter("professore", id_professore)
			  .getResultList().get(0);
		
		return cattedra;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
