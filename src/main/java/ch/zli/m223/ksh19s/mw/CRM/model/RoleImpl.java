package ch.zli.m223.ksh19s.mw.CRM.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Role")
public class RoleImpl implements Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;
	
	@ManyToOne
	private AppUserImpl appUser;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getRole() {
		return role;
	}

	protected RoleImpl() {/*for JPA only*/}
	
	public RoleImpl(String role, AppUserImpl appUser) {
		this.role = role;
		this.appUser = appUser;
	}
}
