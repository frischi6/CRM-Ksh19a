package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import java.util.ArrayList;
import java.util.List;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;

public class UserDto {
	public Long id;
	public String email;
	public List<String> roleList;
	public String hobby;
	public List<String> courseList;
	public List<String> workList;

	public UserDto(AppUser user) {
		id = user.getId();
		email = user.getEmail();
		roleList = new ArrayList<>();
		hobby = user.getHobby();
		courseList = new ArrayList<>();
		workList = new ArrayList<>();

		for (var role : user.getRoles()) {
			roleList.add(role.getRole());
		}

		for (var role : user.getCourses()) {
			courseList.add(role.getCourse());
		}

		for (var role : user.getWorks()) {
			workList.add(role.getWork());
		}

	}
}
