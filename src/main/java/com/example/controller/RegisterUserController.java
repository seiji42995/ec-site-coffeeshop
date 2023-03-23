package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.RegisterUser;
import com.example.form.LoginUserForm;
import com.example.form.RegisterUserForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/register-user")
public class RegisterUserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String index(RegisterUserForm registerUserForm) {
		return "register_admin";
	}
	
	@PostMapping("/insert")
	public String insert(@Validated RegisterUserForm registerUserForm, BindingResult result, Model model) {
		RegisterUser registerUser = new RegisterUser(registerUserForm.getLastName()+registerUserForm.getFirstName(), registerUserForm.getEmail(), registerUserForm.getPassword(), registerUserForm.getZipcode(), registerUserForm.getAddress(), registerUserForm.getTelephone());
		System.out.println(registerUser);
		userService.insert(registerUser);
		
		if(result.hasErrors()) {
			return index(registerUserForm);
		}
		
		return "redirect:/register-user/to-login";
	}
	
	@GetMapping("/to-login")
	public String toLogin(LoginUserForm loginUserForm) {
		return "login";
	}
	
}
