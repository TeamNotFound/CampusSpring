package teamNotFound.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import teamNotFound.daoimpl.AccountDao;

@Component
public class OnAuthenticationSuccessImpl extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AmazonUploadUtil amazonUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String generatedKey = accountDao.getByUsername(authentication.getName()).getUtente().getImageGeneratedName();
		request.getSession().setAttribute("profilePic", amazonUtil.generateUrl(generatedKey));
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
