package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login-user")
public class LoginUserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	@GetMapping("")
	public String index(LoginUserForm loginUserForm) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model, @Validated LoginUserForm loginUserForm, BindingResult result) {
		User user = userService.findByEmailAndPassword(loginUserForm);
		
		if(result.hasErrors()) {
			return "login";
		}
		
		if(user == null) {
			model.addAttribute("message", "ユーザー登録がされていません");
			return "login";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/show/item-list";
	}
}
