package teamNotFound.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtil {

	public String hashPsw(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkPs2(String password, String salt) {
		return BCrypt.checkpw(password, salt);
	}
}
