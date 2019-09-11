package teamNotFound.daoimpl;

import java.util.*;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Professore;



public class ProfessoreDao extends CrudGenerico<Professore, Integer> implements CRUDInterface<Professore , Integer>{
	
	public ProfessoreDao () {
		this.classeT=Professore.class;
	}
	public Professore getByIdWithCorsi(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Professore professore;
		
		try{
			session.beginTransaction();
 
			professore = (Professore) session.get(Professore.class, id);

			Hibernate.initialize(professore.getProfessoriCorsi());
			
			session.getTransaction().commit();

			return professore;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}


