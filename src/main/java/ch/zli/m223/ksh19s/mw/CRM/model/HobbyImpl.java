package ch.zli.m223.ksh19s.mw.CRM.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Hobby")
public class HobbyImpl implements Hobby {

	@Id // damit datenbank diesen als primary key setzt
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hobby;

	@ManyToOne
	private AppUserImpl appUser;

	protected HobbyImpl() {
		/* for JPA only */}

	public HobbyImpl(String hobby, AppUserImpl appUser) {
		this.hobby = hobby;
		this.appUser = appUser;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getHobby() {
		return hobby;
	}

}
