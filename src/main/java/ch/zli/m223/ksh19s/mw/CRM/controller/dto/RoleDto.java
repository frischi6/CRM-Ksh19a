package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import ch.zli.m223.ksh19s.mw.CRM.model.Role;

public class RoleDto {
	public long id;
	public String roleName;
	
	public RoleDto(Role role) {
		id = role.getId();
		roleName = role.getRole();
	}
}
