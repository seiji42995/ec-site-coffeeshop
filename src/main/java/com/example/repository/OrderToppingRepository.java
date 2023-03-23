package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.form.ShoppingCartForm;

@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public void insert(ShoppingCartForm shoppingCartForm, Integer orderItemId) {
		String insertSql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId);";
		for (Integer toppingId : shoppingCartForm.getToppingList()) {
			SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", toppingId)
					.addValue("orderItemId", orderItemId);
			template.update(insertSql, param);
		}
	}
}
