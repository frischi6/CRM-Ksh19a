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

@RestController
@RequestMapping("/api/v1")
public class RoleRestController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@GetMapping("/roles")
	List<RoleDTO> getAllRoles() {
		return roleService.getAllRoles().stream().map(user -> new RoleDTO(user)).collect(Collectors.toList());
	}

	@GetMapping("/roles/{id}")
	RoleDTO getRole(@PathVariable("id") Long id) {
		return new RoleDTO(roleService.getRole(id));
	}

	@PostMapping("/roles")
	RoleDTO insertUser(@RequestBody RoleInputDto roleData) {
		return new RoleDTO(roleService.insertRole(roleData.role));
	}

	@DeleteMapping("/roles/{id}")
	void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRoleById(id);
	}

}
