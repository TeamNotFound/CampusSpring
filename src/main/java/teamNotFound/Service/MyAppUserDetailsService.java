package teamNotFound.Service;

import java.util.Arrays;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.model.Account;

//Service che recupera le informazione dell utente dal database
	@Service
	//Poich  la classe   annotata con @Service, Spring provveder  a crearne un istanza nell application context. 
	//Spring Security rilever  la presenza di un istanza  concreta  di UserDetailsService nel contesto dell applicazione e
	//utilizzer  il nuovo servizio per recuperare i dati necessari all autenticazione.
	public class MyAppUserDetailsService implements UserDetailsService {
		@Autowired
		private AccountDao dao;
		//Assegnamo la classe al nostro file di log
		private static final Logger logger = LoggerFactory.getLogger(MyAppUserDetailsService.class);	
		//PRENDE LE INFORMAZIONI DALLA FORM SE NON  SPECIFICATA DA FORM LOGIN PRENDE LE INFO DALLA FORM DI DEFAULT
		//SE SPECIFICATA LA FORM TRAMITE FORMLOGIN PRENDE LE INFORMAZIONI DA FORMLOGIN
		@Override
		public UserDetails loadUserByUsername(String username) {
			UserDetails userDetails = null;
			try {
				//Prende un account tramite username dal database
				Account acc = dao.getByUsername(username);
				//Assegna attraverso un oggetto GrantedAuthority l'autorit  dell'utente trovato, nel mio caso
				//viene assegnato un ruolo che pu  essere una stringa fra Amministratore,God,Utente.
				GrantedAuthority authority = new SimpleGrantedAuthority(acc.getRuolo().getRuolo());
				//Assegnamo a userDetails i dettagli dell'utente trovato ( Es. Admin admin) e trattiamo l'autorit  come una lista
				userDetails = (UserDetails) new User(acc.getUsername(), acc.getPassword(), Arrays.asList(authority));
				//Se l'utente non viene trovato, o una di queste operazioni non dovrebbe andare a buon fine andremo nel catch
			} catch (Exception e) {
				logger.warn("Utente non trovato");
			}
			//ritorniamo i dettagli dell'utente oramai riempito con username, password e una lista di autorit 
			return userDetails;
		}
	} 

