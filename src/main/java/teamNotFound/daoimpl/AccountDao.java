package teamNotFound.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.Account;

@Repository
public class AccountDao extends CrudGenerico<Account, Integer> {
	
	@Autowired
	BCryptPasswordEncoder cripta;
	@Autowired
	public AccountDao () {
		this.classeT=Account.class;
	}
	
	public Account getByUsername(String username) {
		try{
			Account account = this.entity.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
							  .setParameter("username", username)
							  .getSingleResult();
			return account;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void inserimentoAcc(Account a) {
		// TODO Auto-generated method stub
		a.getUsername();
		a.getPassword();
		//Cirpta la password dell'utente passato
		a.setPassword(cripta.encode(a.getPassword()));
		entity.persist(a);
		entity.flush();
		entity.clear();
	}

	
}
