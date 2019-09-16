package teamNotFound.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//SICCOME E' UNA PAGINA CONFIGURAZIONE VIENE MAPPATA CON CONFIGURATION
@Configuration
public class WebMvcConfig {
	//IN QUESTA CLASSE CREIAMO UNIISTANZA DELL'OGGETTO BCryptPasswordEncoder
	//L'OGGETTO VERRA' ISTANZIATO TRAMITE @BEAN QUINDI AGGIUNTI NEL IoC CONTAINER E RICHIAMATO QUANDO UTILE 
	@Bean
	//Utilizzeremo questo metodo nel momento in cui vorremo decriptare la password criptata nel nostro database
	//Es. Nel dao ci sar  un metodo di inserimento, che istanzier  un oggetto di tipo BCryptPasswordEncoder
	//al quale andremo a specificare l'operazione di codifica.
	
	//RICORDA: Per rendere possibile la criptazione della password all'interno del database   necessario specificare una
	//lunghezza pari a 60 caratteri, questo perch  il metodo gener  una password casuale di almeno 60 caratteri.
	
	//Metodo per decriptare la password.
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
}

