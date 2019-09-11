package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Account;

@Repository
public class AccountDao extends CrudGenerico<Account, Integer> implements CRUDInterface<Account , Integer> {
	
	public AccountDao () {
		this.classeT=Account.class;
	}
	
	public Account getByUsername(String username) {
		try{
			Account account = this.entity.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
							  .setParameter("username", username)
							  .getResultList().get(0);
			return account;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
