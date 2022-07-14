package ch.zli.m223.ksh19s.mw.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Course;
import ch.zli.m223.ksh19s.mw.CRM.model.CourseImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Schoolclass;

public interface CourseRepository extends JpaRepository<CourseImpl, Long> {

	Schoolclass schoolclass = new Schoolclass();

	default Course insert(int indexCourse, AppUser appUser) {

		// cast to impl
		AppUserImpl studentImpl = (AppUserImpl) appUser;

		// create new Role
		CourseImpl newCourse = new CourseImpl(schoolclass.getSchoolclass(indexCourse), studentImpl);

		// save Role to DB
		CourseImpl savedNewCourse = save(newCourse);

		// update users role list
		studentImpl.addCourseToList(savedNewCourse);

		// return new Role
		return savedNewCourse;
	}

}
