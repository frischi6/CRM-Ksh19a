package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import ch.zli.m223.ksh19s.mw.CRM.model.Course;

public class CourseDTO {
	public String courseName;

	public CourseDTO(Course course) {
		courseName = course.getCourse();
	}
}
