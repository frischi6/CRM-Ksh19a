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

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.RoleDto;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.UserDto;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.UserInputDto;
import ch.zli.m223.ksh19s.mw.CRM.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	List<UserDto> getAllUsers() {
		return userService.getAllUsers().stream()
				.map(user -> new UserDto(user))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/users/{id}")
	UserDto getUser(@PathVariable("id") Long id) {
		return new UserDto(userService.getUser(id));
	}
	
	@PostMapping("/users")
	UserDto insertUser(@RequestBody UserInputDto userData) {
		return new UserDto(userService.insertUser(userData.name));
	}
	
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/users/{id}/roles")
	List<RoleDto> getRolesForUser(@PathVariable("id") Long id) {
		var user =userService.getUser(id);
		return user.getRoles().stream()
			.map(r -> new RoleDto(r))
			.collect(Collectors.toList());
	}
}








