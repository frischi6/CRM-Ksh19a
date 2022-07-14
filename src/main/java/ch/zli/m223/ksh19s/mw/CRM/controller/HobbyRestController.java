package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.HobbyDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.WorkDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.WorkInputDto;
import ch.zli.m223.ksh19s.mw.CRM.service.HobbyService;

@RestController
@RequestMapping("/api/v1")
public class HobbyRestController {

	@Autowired
	private HobbyService hobbyService;

	@GetMapping("/hobbies")
	List<HobbyDTO> getAllHobbies() {
		return hobbyService.getAllHobbies().stream().map(user -> new HobbyDTO(user)).collect(Collectors.toList());
	}

	@GetMapping("/works/{id}")
	WorkDTO getWork(@PathVariable("id") Long id) {
		return new WorkDTO(hobbyService.getWork(id));
	}

	@PostMapping("/works")
	WorkDTO insertWork(@RequestBody WorkInputDto workData) {
		return new WorkDTO(hobbyService.insertWork(workData.work));
	}

	@DeleteMapping("/works/{id}")
	void deleteWork(@PathVariable("id") Long id) {
		hobbyService.deleteWorkById(id);
	}

}
