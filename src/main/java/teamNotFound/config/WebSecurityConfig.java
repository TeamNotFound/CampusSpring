package teamNotFound.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import teamNotFound.Service.MyAppUserDetailsService;

@Configuration
@EnableWebSecurity
//l’annotazione @EnableWebSecurity, definita da Spring Security, abilita l’integrazione con Spring MVC e 
//permette di personalizzare le logiche di autenticazione effettuando l’override dei metodi dichiarati da 
//WebSecurityConfigurerAdapter;
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Prendo l'istanza del metodo della classe WebMvcConfig, che ora sarà presente nel container
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
		//L'utente verrà autentificato attraverso i metodi: userDetailsService e passwordEncoder
		
		//1)userDetailsService non è altro che un contenitore di informzioni sull'utente. ( contiene user, pass e autorita)
		//vedi classe userDetailsService per maggiorni informazioni.
		//2)passwordEncoder ci permetterà di decodificare la password del nostro utente trovato.
		
		try {
			auth.userDetailsService(myAppUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
		} catch (Exception e) {
			logger.error("Utente non trovato");
		}
	}
	
	//Ti consente di configurare la visibilità delle pagine http in base alle autorità in base all'utente loggato
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
		.and().formLogin() 
			.loginPage("/session/login.jsp")
			.loginProcessingUrl("/login")
            .permitAll()
        .and().logout()    
			.logoutUrl("/logout") 
			.logoutSuccessUrl("/login")
		.and().exceptionHandling() 
			.accessDeniedPage("/access-denied"); 
	} 	

}