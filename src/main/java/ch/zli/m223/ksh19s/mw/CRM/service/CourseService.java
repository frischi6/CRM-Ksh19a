package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.List;

import ch.zli.m223.ksh19s.mw.CRM.model.Course;

public interface CourseService {

	List<Course> getAllCourses();

	Course getCourse(Long id);

	Course insertCourse(String course);

	void deleteCourseById(Long id);

}
