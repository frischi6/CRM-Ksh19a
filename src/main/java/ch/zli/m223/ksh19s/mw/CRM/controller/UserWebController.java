package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

	@GetMapping("/admin")
	String gotoAdminPage() {
		return "admin_page";
	}

	@GetMapping("/user")
	String gotoUserPage() {
		return "user_page";
	}

	@GetMapping("/logedin")
	String gotoLogedInPage() {
		return "loged_in_page";
	}

	@GetMapping("/deleteUser/{id}")
	String deleteUserWeb(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "redirect:/userlist";
	}

}
