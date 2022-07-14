package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh19s.mw.CRM.exception.InvalidArgumentException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserAllreadyExistsException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserNotFoundException;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.Course;
import ch.zli.m223.ksh19s.mw.CRM.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	private AppUser user;

	@Override
	public List<Course> getAllCourses() {
		return new ArrayList<>(courseRepository.findAll());
	}

	@Override
	public Course getCourse(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> {
			throw new UserNotFoundException("Invalid user Id " + id);
		});
	}

	@Override
	public Course insertCourse(String course) {
		if (course == null)
			throw new InvalidArgumentException("Name must not be null");
		// If (user with userName exists) then throw UserAllreadyExistsException
		if (courseRepository.findCourseByCourse(course).isPresent()) {
			throw new UserAllreadyExistsException("User with name" + course + " already exists");
		}
		return courseRepository.insert(course, user);
	}

	@Override
	public void deleteCourseById(Long id) {
		if (id == null)
			throw new InvalidArgumentException("Id must not be null");
		if (courseRepository.findById(id).isEmpty()) {
			return;
		}
		courseRepository.deleteById(id);
	}

}
