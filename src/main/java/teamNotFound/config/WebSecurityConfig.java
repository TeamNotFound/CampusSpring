package teamNotFound.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
			.antMatchers("/**").permitAll()			
		.and().formLogin() 
			.loginPage("/login")
			.loginProcessingUrl("/login")
            .permitAll()
        .and().logout()    
			.logoutUrl("/logout") 
			.logoutSuccessUrl("/login")
		.and().exceptionHandling() 
			.accessDeniedPage("/access-denied"); 
	} 	

}