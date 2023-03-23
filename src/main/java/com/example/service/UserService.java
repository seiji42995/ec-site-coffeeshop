package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.RegisterUser;
import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.repository.UserRepository;

/**
 * ユーザー情報を操作するサービスクラス.
 * 
 * @author seiji_kitahara
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 入力されたメールアドレスとパスワードで一致するユーザー情報を取得する.
	 * 
	 * @param loginUserForm
	 * @return
	 */
	public User findByEmailAndPassword(LoginUserForm loginUserForm) {
		User user = userRepository.findByEmailAndPassword(loginUserForm);
		return user;
	}
	
	public void  insert(RegisterUser registerUser) {
		userRepository.insert(registerUser);
	}
}
