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

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.CourseDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.RoleDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.UserDto;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.UserInputDto;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.WorkDTO;
import ch.zli.m223.ksh19s.mw.CRM.service.UserService;

/**
 * Rest Controller that shows Data in database format
 * 
 * @author sarah
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class UserRestController {

	@Autowired
	private UserService userService;

	/**
	 * shows all users
	 * 
	 * @return List with all users
	 */
	@GetMapping("/users")
	List<UserDto> getAllUsers() {
		return userService.getAllUsers().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
	}

	/**
	 * shows all data of one user
	 * 
	 * @param id of the user
	 * @return data of the user
	 */
	@GetMapping("/users/{id}")
	UserDto getUser(@PathVariable("id") Long id) {
		return new UserDto(userService.getUser(id));
	}

	/**
	 * inserts a new user
	 * 
	 * @param userData
	 * @return new userDTO
	 */
	@PostMapping("/users")
	UserDto insertUser(@RequestBody UserInputDto userData) {
		return new UserDto(userService.insertUser(userData.name, userData.password));
	}

	/**
	 * deletes a user
	 * 
	 * @param id of the user which will be deleted
	 */
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	/**
	 * shows all roles of one user
	 * 
	 * @param id of user
	 * @return all roles of one user
	 */
	@GetMapping("/users/{id}/roles")
	List<RoleDTO> getRolesForUser(@PathVariable("id") Long id) {
		var user = userService.getUser(id);
		return user.getRoles().stream().map(r -> new RoleDTO(r)).collect(Collectors.toList());
	}

	/**
	 * shows all works of one user
	 * 
	 * @param id of user
	 * @return all works of one user
	 */
	@GetMapping("/users/{id}/works")
	List<WorkDTO> getWorksForUser(@PathVariable("id") Long id) {
		var user = userService.getUser(id);
		return user.getWorks().stream().map(r -> new WorkDTO(r)).collect(Collectors.toList());
	}

	/**
	 * shows all courses of one user
	 * 
	 * @param id of user
	 * @return all courses of one user
	 */
	@GetMapping("/users/{id}/courses")
	List<CourseDTO> getCoursesForUser(@PathVariable("id") Long id) {
		var user = userService.getUser(id);
		return user.getCourses().stream().map(r -> new CourseDTO(r)).collect(Collectors.toList());
	}

	/**
	 * shows hobby of one user
	 * 
	 * @param id of user
	 * @return hobby of one user
	 */
	@GetMapping("/users/{id}/hobby")
	String getHobbiesForUser(@PathVariable("id") Long id) {
		var user = userService.getUser(id);
		return user.getHobby();// .stream().map(r -> new HobbyDTO(r)).collect(Collectors.toList());
	}

}
