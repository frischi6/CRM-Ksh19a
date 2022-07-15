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

	/**
	 * shows all users with its data
	 * 
	 * @param model with data
	 * @return page with all data
	 */
	@GetMapping("/userlist")
	String getUserList(Model model) {
		List<AppUser> userList = userService.getAllUsers();
		model.addAttribute("users", userList);
		return "userList";
	}

	/**
	 * when user is loged in with role admin
	 * 
	 * @return page with all data
	 */
	@GetMapping("/admin")
	String gotoAdminPage() {
		return "redirect:/userlist";
	}

	/**
	 * when user is loged in with role user
	 * 
	 * @return page with all data
	 */
	@GetMapping("/user")
	String gotoUserPage() {
		return "redirect:/userlist";
	}

	/**
	 * when user has loged in
	 * 
	 * @return page with all data
	 */
	@GetMapping("/logedin")
	String gotoLogedInPage() {
		return "redirect:/userlist";
	}

	/**
	 * when a user gets deleted
	 * 
	 * @param id of user to delete
	 * @return page with all data
	 */
	@GetMapping("/deleteUser/{id}")
	String deleteUserWeb(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "redirect:/userlist";
	}

	/**
	 * when user wants to add a new user
	 * 
	 * @param model of data
	 * @return page where data of new user can be inserted
	 */
	@RequestMapping("/addNewUser")
	public String addNewUser(Model model) {
		return "showUserNewForm";
	}

	/**
	 * after new user was added
	 * 
	 * @param model of data
	 * @return page with all data
	 */
	@RequestMapping("/showUsers")
	public String showUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "userlist";
	}

	/**
	 * saves a new user
	 * 
	 * @param model    of data
	 * @param name     of user
	 * @param password of user
	 * @param roles    of user
	 * @param hobby    of user
	 * @param works    of user
	 * @param courses  of user
	 * @return page with all data
	 */
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
		return "redirect:/userlist";
	}

}
