package teamNotFound.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;

import teamNotFound.config.AmazonUploadUtil;
import teamNotFound.dao.CRUDInterface;
import teamNotFound.daoimpl.AccountDao;
import teamNotFound.daoimpl.ProfessoreDao;
import teamNotFound.daoimpl.StudenteDao;
import teamNotFound.model.Account;
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

	public String changePic(HttpSession session ,@RequestParam("image") MultipartFile image, Principal principal) {
		Future<String> generatedKey;

		try {
			File imageFilezed = amazonUtil.convert(image);
			generatedKey = amazonUtil.upload(imageFilezed);
		} catch (AmazonClientException | InterruptedException | IOException e) {
			e.printStackTrace();
			return "redirect:/profilePicForm?error";
		}


		Account account = accountDao.getByUsername(principal.getName());
		Utente utente = account.getUtente();

		CRUDInterface crud;
		if(account.getRuolo().getRuolo().equals("PROFESSORE") || 
				account.getRuolo().getRuolo().equals("RETTORE")) {
			crud = professoreDao;
		} else {
			crud = studenteDao;
		}

		amazonUtil.setImageToUserAndSession(utente, crud, session, generatedKey);
		
		return "redirect:/";
	}
}
