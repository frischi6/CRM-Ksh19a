package ch.zli.m223.ksh19s.mw.CRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/addNewUser")
	public String addNewUser(Model model) {
		// show a new user
		return "showUserNewForm";
	}

	@RequestMapping("/showUsers")
	public String showUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "userlist";
	}

	@RequestMapping("/saveUser")
	public String saveNewUser(Model model, @RequestParam("username") String name,
			@RequestParam("password") String password,
			@RequestParam(value = "rolenames", defaultValue = "") String[] roles, @RequestParam("hobby") String hobby,
			@RequestParam(value = "worknames", defaultValue = "") String[] works,
			@RequestParam(value = "coursenames", defaultValue = "") String[] courses) {
		// create a new user
		AppUser user = userService.insertNewUser(name, password, roles, hobby, works, courses);
		if (user == null) {
			// Show the form again
			model.addAttribute("error", "userName already exists");
			model.addAttribute("username", name);
			model.addAttribute("password", password);
			return "showUserNewForm";
		}
		return "redirect:/showUsers";
	}

}
