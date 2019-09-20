package teamNotFound.config;

import org.modelmapper.ModelMapper;
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
	//Es. Nel dao ci sarà un metodo di inserimento, che istanzierà un oggetto di tipo BCryptPasswordEncoder
	//al quale andremo a specificare l'operazione di codifica.
	
	//RICORDA: Per rendere possibile la criptazione della password all'interno del database è necessario specificare una
	//lunghezza pari a 60 caratteri, questo perchè il metodo generà una password casuale di almeno 60 caratteri.
	
	//Metodo per decriptare la password.
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}