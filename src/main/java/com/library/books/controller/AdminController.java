package com.library.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.books.AdminEntity;
import com.library.books.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	@RequestMapping("/forgotpasswordPage")
	public String forgotPasswordPage() {
		return "forgotpasword";
	}

	@RequestMapping("/forgotpassword")
	public String forgotpassword(Model m, @RequestParam String email, @RequestParam String pswd,
			@RequestParam String cpswd) {
		if (pswd.equals(cpswd)) {
			if (adminService.forgotpasswordservice(email, pswd) == 1) {
				m.addAttribute("mssg", "Password Changed Successfully");
				return "login";
			} else {
				m.addAttribute("mssg", "Oop's email does not exist");
				return "forgotpasword";
			}
		} else {
			m.addAttribute("mssg", "Password and Confirm Password do not match");
			return "forgotpasword";
		}
	}

	@RequestMapping("/loginPage")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signuppage")
	public String signupPage() {
		return "signup";
	}

	@GetMapping("/signup")

	public String signUp(Model m, @RequestParam String name, @RequestParam String email, @RequestParam String phone,
			@RequestParam String password) {

		m.addAttribute("mssg", adminService.signupService(new AdminEntity(name, email, phone, password)));
		return "welcome";
	}

	@GetMapping("/loginParam")
	public String login(Model m, @RequestParam String email, @RequestParam String password) {
		if (adminService.loginService(email, password) != null) {
			m.addAttribute("mssg", adminService.loginService(email, password));
			return "welcome";
		} else {
			m.addAttribute("mssg", "Invalid Password");
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}

}
