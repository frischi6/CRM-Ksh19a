package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.HobbyDTO;
import ch.zli.m223.ksh19s.mw.CRM.service.HobbyService;

/**
 * Rest Controller that shows Data in database format
 * 
 * @author sarah
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class HobbyRestController {

	@Autowired
	private HobbyService hobbyService;

	/**
	 * shows all hobbies
	 * 
	 * @return List with all hobbies
	 */
	@GetMapping("/hobbies")
	List<HobbyDTO> getAllHobbies() {
		return hobbyService.getAllHobbies().stream().map(user -> new HobbyDTO(user)).collect(Collectors.toList());
	}

	/**
	 * shows hobby the user with this id has noted
	 * 
	 * @param id of the user
	 * @return hobby the user has noted
	 */
	@GetMapping("/hobbies/{id}")
	HobbyDTO getHobby(@PathVariable("id") Long id) {
		return new HobbyDTO(hobbyService.getHobby(id));
	}

	/*
	 * @PostMapping("/hobbies") HobbyDTO insertHobby(@RequestBody HobbyInputDto
	 * hobbyData) { return new HobbyDTO(hobbyService.insertHobby(hobbyData.hobby));
	 * }
	 */

	/**
	 * deletes a hobby
	 * 
	 * @param id of the user which hobby will be deleted
	 */
	@DeleteMapping("/hobbies/{id}")
	void deleteHobby(@PathVariable("id") Long id) {
		hobbyService.deleteHobbyById(id);
	}

}
