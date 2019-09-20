package teamNotFound.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.model.Facolta;

@Controller
public class SearchController {
	
	@Autowired
	private FacoltaDao facoltaDao;

	@GetMapping("/search")
	public String searchFacolta(@RequestParam String facolta, Model model) {
		List<Facolta> searchResults = facoltaDao.search(facolta);
		
		if(searchResults.size() == 1) {
			return "redirect:/Facolta/"+searchResults.get(0).getId();
		} else {
			model.addAttribute("searchResults", searchResults);
			return "searchResults";
		}
	}
}
