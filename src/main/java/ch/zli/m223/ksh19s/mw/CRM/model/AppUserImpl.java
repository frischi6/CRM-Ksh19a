package ch.zli.m223.ksh19s.mw.CRM.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity(name="AppUser")
@SuppressWarnings("serial")
public class AppUserImpl implements AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String name;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<RoleImpl> roles;

	private String passwordHash;
	
	protected AppUserImpl() {
		/* for JPA only */
		roles = new ArrayList<>();
	}
	
	public AppUserImpl(String name) {
		this();
		this.name = name;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getEmail() {
		return name;
	}

	@Override
	public List<Role> getRoles() {
		return new ArrayList<>(roles);
	}

	public void addRoleToList(RoleImpl newRole) {
		roles.add(newRole);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return passwordHash;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
