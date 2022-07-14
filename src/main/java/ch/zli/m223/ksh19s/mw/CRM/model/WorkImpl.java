package ch.zli.m223.ksh19s.mw.CRM.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Work")
public class WorkImpl implements Work {

	@Id // damit datenbank diesen als primary key setzt
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String work;

	@ManyToOne
	private AppUserImpl appUser;

	protected WorkImpl() {
		/* for JPA only */}

	public WorkImpl(String work, AppUserImpl appUser) {
		this.work = work;
		this.appUser = appUser;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getWork() {
		return work;
	}

}
