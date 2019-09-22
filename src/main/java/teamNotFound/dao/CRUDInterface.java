package teamNotFound.dao;

import java.util.ArrayList;

public interface CRUDInterface<T,K> {
	public void inserimento(T element);
	public T getById(K id);
	public ArrayList<T> getAll();
	public void update(T element);
	public void remove(T element);
}
