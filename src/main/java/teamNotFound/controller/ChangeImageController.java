package teamNotFound.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;

import teamNotFound.config.AmazonUploadUtil;
import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.daoimpl.StudenteDao;
import teamNotFound.model.Account;
import teamNotFound.model.Professore;
import teamNotFound.model.Studente;
import teamNotFound.model.Utente;

@Controller
public class ChangeImageController {

	@Autowired
	private AmazonUploadUtil amazonUtil;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private StudenteDao studenteDao;
	@Autowired
	private ProfessoreDao professoreDao;

	@GetMapping("/ProfilePic")
	public String profilePic() {
		return "profilePicForm";
	}

	@PostMapping("/ProfilePic")
	public String changePic(HttpSession session, @RequestParam("image") MultipartFile image, Principal principal) {
		String generatedKey;

		try {
			generatedKey = amazonUtil.upload(image);
		} catch (AmazonClientException | InterruptedException | IOException e) {
			e.printStackTrace();
			return "redirect:/profilePicForm?error";
		}


		Account account = accountDao.getByUsername(principal.getName());
		Utente utente = account.getUtente();

		utente.setImageGeneratedName(generatedKey);

		if(account.getRuolo().getRuolo().equals("PROFESSORE") || 
				account.getRuolo().getRuolo().equals("RETTORE")) {
			professoreDao.update((Professore) utente);
		} else {
			studenteDao.update((Studente) utente);
		}

		session.setAttribute("profilePic", amazonUtil.generateUrl(generatedKey));

		return "redirect:/";
	}
}
