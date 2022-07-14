package ch.zli.m223.ksh19s.mw.CRM.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Course")
public class CourseImpl implements Course {

	@Id // damit datenbank diesen als primary key setzt
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String course;

	@ManyToOne
	private AppUserImpl appUser;

	protected CourseImpl() {
		/* for JPA only */}

	public CourseImpl(String course, AppUserImpl appUser) {
		this.course = course;
		this.appUser = appUser;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getCourse() {
		return course;
	}

}
