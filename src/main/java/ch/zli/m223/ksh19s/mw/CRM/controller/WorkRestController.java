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

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.WorkDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.WorkInputDto;
import ch.zli.m223.ksh19s.mw.CRM.service.WorkService;

/**
 * Rest Controller that shows Data in database format
 * 
 * @author sarah
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class WorkRestController {

	@Autowired
	private WorkService workService;

	/**
	 * shows all works
	 * 
	 * @return List with all works
	 */
	@GetMapping("/works")
	List<WorkDTO> getAllWorks() {
		return workService.getAllWorks().stream().map(user -> new WorkDTO(user)).collect(Collectors.toList());
	}

	/**
	 * shows first work the user with this id has selected
	 * 
	 * @param id of the user
	 * @return first work the user has selected
	 */
	@GetMapping("/works/{id}")
	WorkDTO getWork(@PathVariable("id") Long id) {
		return new WorkDTO(workService.getWork(id));
	}

	/**
	 * inserts a work
	 * 
	 * @param workName
	 * @return new inserted workDTO
	 */
	@PostMapping("/works")
	WorkDTO insertWork(@RequestBody WorkInputDto workData) {
		return new WorkDTO(workService.insertWork(workData.work));
	}

	/**
	 * deletes a work
	 * 
	 * @param id of the user which work will be deleted
	 */
	@DeleteMapping("/works/{id}")
	void deleteWork(@PathVariable("id") Long id) {
		workService.deleteWorkById(id);
	}

}
