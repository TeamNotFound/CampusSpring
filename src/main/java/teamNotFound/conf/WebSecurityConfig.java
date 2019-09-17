package teamNotFound.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import teamNotFound.service.MyAppUserDetailsService;

//le nostre regole di autenticazione le definiamo all interno del metodo configure.
	//Indica al container di Spring che questo sar  un file di configurazione
	@Configuration
	@EnableWebSecurity
	//l annotazione @EnableWebSecurity, definita da Spring Security, abilita l integrazione con Spring MVC e 
	//permette di personalizzare le logiche di autenticazione effettuando l override dei metodi dichiarati da 
	//WebSecurityConfigurerAdapter;
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
		//Prendo l'istanza del metodo della classe WebMvcConfig, che ora sar  presente nel container
		//Autowired effettua L'IoC
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		//Prendo l'istanza del metodo della classe MyAppUserDetailsService, che ora sara presente nel contaier
		@Autowired
		private MyAppUserDetailsService myAppUserDetailsService;
		//Passiamo la configurazione al file logger, dove saranno contenute tutte le informazioni di log
		private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);	
		//Usiamo l'istanza del metodo presente nel container 
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			//L'utente verr  autentificato attraverso i metodi: userDetailsService e passwordEncoder
			
			//1)userDetailsService non   altro che un contenitore di informzioni sull'utente. ( contiene user, pass e autorita)
			//vedi classe userDetailsService per maggiorni informazioni.
			//2)passwordEncoder ci permetter  di decodificare la password del nostro utente trovato.
			
			try {
				auth.userDetailsService(myAppUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
			} catch (Exception e) {
				logger.error("Utente non trovato");
			}
		}
		@Override
		 public void configure(WebSecurity web) throws Exception {
		        web
		            .ignoring()
		            .antMatchers("/resources/**", "/static/**","/webjars/**");
		    }
		//Ti consente di configurare la visibilit  delle pagine http in base alle autorit  in base all'utente loggato
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//AUTORIZZA LE RICHIESTE HTTP NELLA BARRA DEGLI INDIRIZZI
			http.authorizeRequests()
			//antMatchers resta in ascolto e intercetta gli indirizzi restituiti nella barra degli indirizzi
			//quando accediamo ad una sezione, se questa matcha, possiamo assegnare un permesso o revocarlo
			//in questo caso fstiamo specificando che tutti gli indirizzi che iniziano con "/" ( in questo caso il mio index)
			//hanno tutti il permesso di accedervi. Se non avessimo dato il permesso a tutti, sarebbe stato impossibile accedervi
				.antMatchers("/FirstAccess").permitAll()
			//In questo caso specifichiamo che ogni risorsa che si trova dopo il path iniziale, ha bisogno di un'autorit 
			//di tipo god per accedervi(l'autorit  viene assegnata all'utente in MyAppUserDetailsService)
			//Gli asterischi indicano che il contenuto che segue al path "/" dovr  rispondere all'autorit  "God"
				 .antMatchers("/**").permitAll()
				 
				// hasAnyAuthority permette di dare l'autorit  a pi  enti.
				//connettore alla form
				.and()
				//Con form login specificheremo che dovr  crearsi a questo punto una form che ci chieder  l'accesso ai dati
				//se non specifichiamo una form personale verr  generata una di default. In genere una buona form per dati
				//sensibili deve contenere il metodo post per l'invio dei dati e l'inserimento di un user e una password
			.formLogin() 
			//Specifichiamo l'indirizzo base a cui ver  eseguita la login, nel mio caso questo indirizzo e accessibile a tutti,
			//senza un'autentificazione. Infatti se si prova a navigare nella barra degli indirizzi, si verr  sempre spediti 
			//sulla pagina di login di default
			//Accediamo a login page tramite la jsp della login che ha come action lo stesso indirizzo di login page.
			.loginPage("/")
			//Una volta che dalla jsp manderemo tramite post i dati a questo metodo, username e password parameter prenderanno
			//i parametri da noi passati dalla jsp.
			
			//imposta l'username tramire il parametro useraname
	            .usernameParameter("username")
	          //imposta password tramire il parametro password
	            .passwordParameter("password")
	          //UNA VOLTA IDENTIFICATI USERNAME E PASSWORD SCRIVE L'INDIRIZZO /ecommerce A CUI RISPONDERA' LA SERVLET
	           
	          //NEL CASO CI SIA UN ERRORE POSSIAMO SPECIFICARE UNA PAGINA PER GESTIRE L'ERRORE
	           
	            //CONNETTORE AND AI CONTROLLI DI CSRF ( ATTACCHI DA APPLICATIVI ESTERNI)
	       .and().csrf().disable()
	       //LOGOUT ASCOLTA NELL'INDIRIZZO /LOGOUT, NEL MOMENTO IN CUI QUESTO INDIRIZZO VIENE RICHIAMATO, GLI UTENTI IN SESSIONE
	       //VERRANNO SLOGGATI, LA CONFIGURAZIONE E I DETTAGLI DELL'UTENTE PULITI E SI VERRA' INVIATI ALLA PAGINA DI LOGIN 
	       //INSIEME AD UN PARAMETRO "SUCCESS", IN MODO DA DIRE NELLA JSP CHE SE IL PARAMETRO SUCCESS E' DIVERSO DA NULL 
	      //ALLORA SEI SLOGGATO CON SUCCESSO.
			.logout()    
			//NEL CASO VOLESSIMO SPECIFICARE UNA PAGINA DI LOGOUT PERSONALIZZATA, POTREMMO UTILIZZARE LOGOUTURL
			.logoutUrl("/appLogout") 
			//SE IL LOGOUT AVVIENE CON SUCCESSO VENIAMO RISPEDITI ALLA PAGINA DI LOGIN
			.logoutSuccessUrl("/")
			//CONTROLLANDO LE ECCEZIONI LANCIATI DALLA CONFIGURAZIONE
			.and().exceptionHandling() 
			//NEL MOMENTO IN CUI C'E' UN ERRORE AD UNA PAGINA, L'UTENTE VERRA' SPEDITO A QUESTA PAGINA DI ACCESSO NEGATO
			.accessDeniedPage("/access-denied"); 
		} 	

	}
