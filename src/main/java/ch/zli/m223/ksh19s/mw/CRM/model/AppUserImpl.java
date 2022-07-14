package ch.zli.m223.ksh19s.mw.CRM.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "AppUser")
@SuppressWarnings("serial")
public class AppUserImpl implements AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RoleImpl> roles;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<CourseImpl> courses;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<HobbyImpl> hobbies;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<WorkImpl> works;

	private String passwordHash;

	protected AppUserImpl() {
		/* for JPA only */
		roles = new ArrayList<>();
		courses = new ArrayList<>();
		hobbies = new ArrayList<>();
		works = new ArrayList<>();

	}

	public AppUserImpl(String name, String password) {
		this();
		this.name = name;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.passwordHash = encoder.encode(password);
	}

	public AppUserImpl(String name, String password, String[] roleNames) {
		this();
		this.name = name;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.passwordHash = encoder.encode(password);
		setRoles(roleNames);
	}

	public void setRoles(String... roleNames) {
		this.roles = new ArrayList<>();
		for (String role : roleNames) {
			addRoleToList(new RoleImpl(role, this));
		}
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
	public List<Course> getCourses() {
		return new ArrayList<>(courses);
	}

	public void addCourseToList(CourseImpl newCourse) {
		courses.add(newCourse);
	}

	@Override
	public List<Hobby> getHobbies() {
		return new ArrayList<>(hobbies);
	}

	public void addHobbyToList(HobbyImpl newHobby) {
		hobbies.add(newHobby);
	}

	@Override
	public List<Work> getWorks() {
		return new ArrayList<>(works);
	}

	public void addWorkToList(WorkImpl newWork) {
		works.add(newWork);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
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

	@Override
	public void deleteUserById(Long id) {
		courses.remove(0);
	}

}