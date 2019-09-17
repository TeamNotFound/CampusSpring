package teamNotFound.Service;




import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import teamNotFound.daoimpl.AccountDao;
import teamNotFound.model.Account;


//Service che recupera le informazione dell’utente dal database
@Service
//Poiché la classe è annotata con @Service, Spring provvederà a crearne un’istanza nell’application context. 
//Spring Security rileverà la presenza di un’istanza “concreta” di UserDetailsService nel contesto dell’applicazione e
//utilizzerà il nuovo servizio per recuperare i dati necessari all’autenticazione.
public class MyAppUserDetailsService implements UserDetailsService{
	@Autowired
	private AccountDao accountDao;
	//Assegnamo la classe al nostro file di log
	private static final Logger logger = LoggerFactory.getLogger(MyAppUserDetailsService.class);	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println("\n\nInside userdetails");
		Account account = accountDao.getByUsername(username);
		if(account != null) {
			System.out.println("Found account\n\n");
			System.out.println(account.getRuolo());
			UserDetails user = User.withUsername(account.getUsername()).password(account.getPassword())
												.roles(account.getRuolo().getRuolo()).build();
			System.out.println("builded");
			return user;
		}else {
			throw new UsernameNotFoundException("USER NOT FOUND!");
		}
	}
} 

