package teamNotFound.daoimpl;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Prenotazione;

public class PrenotazioneDao extends CrudGenerico<Prenotazione, Integer> implements CRUDInterface<Prenotazione , Integer>{

	public PrenotazioneDao () {
		this.classeT=Prenotazione.class;
	}

	
	public Prenotazione getByComposedId(int id_studente, int id_data) {
		try {
				Prenotazione p = (Prenotazione) entity.createQuery("select p from Prenotazione p where p.studente_id = :studente "
																+ "and p.data_appello_id = :data")
								.setParameter("studente", id_studente)
								.setParameter("data", id_data)
								.getResultList().get(0);
		
				return p;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
