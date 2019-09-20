package teamNotFound.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import teamNotFound.daoimpl.FacoltaDao;
import teamNotFound.model.Facolta;
import teamNotFound.model.dto.FacoltaNameOnlyDTO;

@RestController
@RequestMapping("/api")
public class FacoltaApiController {
	
	@Autowired
	private FacoltaDao facoltaDao;
	
	@Autowired
    private ModelMapper modelMapper;

	@GetMapping("/facolta")
	@ResponseBody
	public List<FacoltaNameOnlyDTO> getAllFacolta(){
		
		return facoltaDao.getAll().stream()
								  .map((facolta)-> entityToDto(facolta))
								  .collect(Collectors.toList());
	}
	
	private FacoltaNameOnlyDTO entityToDto(Facolta facolta) {
		return modelMapper.map(facolta, FacoltaNameOnlyDTO.class);
	}
}
