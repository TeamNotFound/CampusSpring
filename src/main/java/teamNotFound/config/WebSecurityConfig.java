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

import teamNotFound.service.MyAppUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MyAppUserDetailsService myAppUserDetailsService;
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);	

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
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
			.antMatchers("/FacoltaCorsi").hasRole("PROFESSORE")
			
			.antMatchers("/inserimentoCatterdra").hasRole("RETTORE")
			.antMatchers("/rimuoviCattedra").hasRole("RETTORE")
			.antMatchers("/rimuoviCattedra/**").hasRole("RETTORE")
			
			.antMatchers("/CorsoFacolta").hasRole("RETTORE")
			.antMatchers("/inserimentoCorso").hasRole("RETTORE")
			.antMatchers("/rimuoviCorso").hasRole("RETTORE")
			.antMatchers("/rimuoviCorso/**").hasRole("RETTORE")
			.antMatchers("/inserimentoFacolta").hasRole("RETTORE")
			.antMatchers("/rimuoviFacolta").hasRole("RETTORE")
			.antMatchers("/rimuoviFacolta/**").hasRole("RETTORE")
			.antMatchers("/visualizza").hasRole("RETTORE")
			.antMatchers("/corso-facolta").hasRole("RETTORE")
			.antMatchers("/Facolta").hasRole("RETTORE")
			.antMatchers("/Facolta/**").hasRole("RETTORE")
			.antMatchers("/Ruolo").hasRole("RETTORE")
			
			.antMatchers("/GestioneProfessori").hasRole("RETTORE")
			.antMatchers("/InserimentoProfessore").hasRole("RETTORE")
			.antMatchers("/Professore/Rimuovi/**").hasRole("RETTORE")
			.antMatchers("/Professore/Promuovi/**").hasRole("RETTORE")
			
			.antMatchers("/AppelloInserimento/**").hasAnyRole("RETTORE","PROFESSORE")
			
			.antMatchers("/Prenotazione/Corso/**").hasRole("STUDENTE")
			.antMatchers("/PrenotazioneDelete").hasRole("STUDENTE")
			.antMatchers("/Prenotazione/PrenotazioneEsame/**").hasRole("STUDENTE")
			.antMatchers("/Prenotazione").hasRole("STUDENTE")
			.antMatchers("/PrenotazioneVisualizza").hasRole("STUDENTE")
			
			.antMatchers("/Esami/Data/**").hasRole("STUDENTE")
			.antMatchers("/Esami/Cattedre").hasAnyRole("RETTORE","PROFESSORE")
			.antMatchers("/Esami/Cattedra/**").hasAnyRole("RETTORE","PROFESSORE")
			.antMatchers("/Esami/Visualizza").hasRole("STUDENTE")
			
			.antMatchers("/*.js").permitAll()
			.antMatchers("/webapp/**").permitAll()
			.antMatchers("/**").permitAll()
		.and().formLogin() 
			.loginPage("/login")
			.loginProcessingUrl("/login")
            .permitAll()
        .and().logout()    
			.logoutUrl("/logout") 
			.permitAll()
		.and().exceptionHandling() 
			.accessDeniedPage("/access-denied"); 
	} 	

}