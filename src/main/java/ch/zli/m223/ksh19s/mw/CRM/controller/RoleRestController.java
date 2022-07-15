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

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.RoleDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.RoleInputDto;
import ch.zli.m223.ksh19s.mw.CRM.service.RoleService;
import ch.zli.m223.ksh19s.mw.CRM.service.UserService;

/**
 * Rest Controller that shows Data in database format
 * 
 * @author sarah
 * 
 */
@RestController
@RequestMapping("/api/v1")
public class RoleRestController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	/**
	 * shows all roles
	 * 
	 * @return List with all roles
	 */
	@GetMapping("/roles")
	List<RoleDTO> getAllRoles() {
		return roleService.getAllRoles().stream().map(user -> new RoleDTO(user)).collect(Collectors.toList());
	}

	/**
	 * shows first role the user with this id has selected
	 * 
	 * @param id of the user
	 * @return first role the user has selected
	 */
	@GetMapping("/roles/{id}")
	RoleDTO getRole(@PathVariable("id") Long id) {
		return new RoleDTO(roleService.getRole(id));
	}

	/**
	 * inserts a role
	 * 
	 * @param roleName
	 * @return new inserted roleDTO
	 */
	@PostMapping("/roles")
	RoleDTO insertUser(@RequestBody RoleInputDto roleData) {
		return new RoleDTO(roleService.insertRole(roleData.role));
	}

	/**
	 * deletes a role
	 * 
	 * @param id of the user which role will be deleted
	 */
	@DeleteMapping("/roles/{id}")
	void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRoleById(id);
	}

}
