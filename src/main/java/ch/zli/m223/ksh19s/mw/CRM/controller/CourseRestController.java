package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh19s.mw.CRM.controller.dto.CourseDTO;
import ch.zli.m223.ksh19s.mw.CRM.controller.dto.CourseInputDto;
import ch.zli.m223.ksh19s.mw.CRM.service.CourseService;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	List<CourseDTO> getAllHobbies() {
		return courseService.getAllCourses().stream().map(user -> new CourseDTO(user)).collect(Collectors.toList());
	}

	@GetMapping("/courses/{id}")
	CourseDTO getCourse(@PathVariable("id") Long id) {
		return new CourseDTO(courseService.getCourse(id));
	}

	@PostMapping("/courses")
	CourseDTO insertCourse(@RequestBody CourseInputDto hobbyData) {
		return new CourseDTO(courseService.insertCourse(hobbyData.course));
	}

	@DeleteMapping("/courses/{id}")
	void deleteCourse(@PathVariable("id") Long id) {
		courseService.deleteCourseById(id);
	}

}
