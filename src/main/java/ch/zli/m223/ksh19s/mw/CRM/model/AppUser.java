package ch.zli.m223.ksh19s.mw.CRM.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface AppUser extends UserDetails {
	public Long getId();
	public String getEmail();
	public List<Role> getRoles();
}
