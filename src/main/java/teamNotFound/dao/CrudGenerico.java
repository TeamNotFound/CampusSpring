package teamNotFound.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public abstract class CrudGenerico<T,K> implements CRUDInterface<T, K>{
	
	@PersistenceContext
	protected EntityManager entity;
	
	protected Class<T> classeT;
	
	public void inserimento(T element) {
		entity.persist(element);
	}
	
	public T getById(K id) {
		T element= (T) entity.find(classeT, (Serializable) id);
		return element;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> getAll(){
		ArrayList<T> elements = (ArrayList<T>) entity.createQuery("select c from " + classeT.getName()+" c").getResultList();
		return elements;
	}
	
	public void update(T element) {
		entity.merge(element);
	}
	
	public void remove(T element) {
		entity.remove(element);		
	}
}
