package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import ch.zli.m223.ksh19s.mw.CRM.model.Role;

public class RoleDTO {
	public String roleName;

	public RoleDTO(Role role) {
		roleName = role.getRole();
	}
}
