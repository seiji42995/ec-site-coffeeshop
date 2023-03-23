package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.RegisterUser;
import com.example.domain.User;
import com.example.form.LoginUserForm;

/**
 * ユーザー情報を操作するリポジトリ.
 * 
 * @author seiji_kitahara
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLE_NAME = "users";

	private final static RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	/**
	 * ユーザー登録画面の入力値をDBに登録する.
	 * @param ユーザー登録画面の入力値
	 */
	public void insert(RegisterUser registerUser) {
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("INSERT INTO " + TABLE_NAME + "(name, email, password, zipcode, address, telephone) ");
		insertSql.append("VALUES(:name, :email, :password, :zipcode, :address, :telephone);");
		SqlParameterSource param = new BeanPropertySqlParameterSource(registerUser);
		template.update(insertSql.toString(), param);
	}

	/**
	 * 入力されたメールアドレスとパスワードで一致するユーザー情報を取得する.
	 * 
	 * @param loginUserForm
	 * @return 一致したユーザー情報
	 */
	public User findByEmailAndPassword(LoginUserForm loginUserForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, email, password, zipcode, address, telephone FROM ");
		sql.append(TABLE_NAME + " WHERE email = :email AND password = :password;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("email", loginUserForm.getEmail())
				.addValue("password", loginUserForm.getPassword());

		List<User> user = template.query(sql.toString(), param, USER_ROW_MAPPER);

		if (user.size() == 0) {
			return null;
		}
		return user.get(0);
	}
	
	public User findByUserId(Integer userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, email, password, zipcode, address, telephone FROM ");
		sql.append(TABLE_NAME + " WHERE id = :userId;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<User> user = template.query(sql.toString(), param, USER_ROW_MAPPER);

		if (user.size() == 0) {
			return null;
		}
		return user.get(0);

	}

}
