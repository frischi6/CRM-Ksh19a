package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Course;
import ch.zli.m223.ksh19s.mw.CRM.model.CourseImpl;

@Repository
public interface CourseRepository extends JpaRepository<CourseImpl, Long> {

	default Course insert(String course, AppUser appUser) {

		// cast to impl
		AppUserImpl studentImpl = (AppUserImpl) appUser;

		// create new Role
		CourseImpl newCourse = new CourseImpl(course, studentImpl);

		// save Role to DB
		CourseImpl savedNewCourse = save(newCourse);

		// update users role list
		studentImpl.addCourseToList(savedNewCourse);

		// return new Role
		return savedNewCourse;
	}

	Optional<CourseImpl> findCourseByCourse(String course); // Spring black magic

}
