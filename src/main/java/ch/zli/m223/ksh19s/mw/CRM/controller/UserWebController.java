package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.service.UserService;

@Controller
public class UserWebController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/userlist")
	String getUserList(Model model) {
		List<AppUser> userList = userService.getAllUsers();
		model.addAttribute("users", userList);
		return "userList";
	}
}
