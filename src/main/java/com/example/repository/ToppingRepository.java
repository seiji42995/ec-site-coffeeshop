package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

@Repository
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {

		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};

	public List<Topping> load() {
		String sql = "SELECT id, name, price_m, price_l FROM toppings;";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		if (toppingList.size() == 0) {
			return null;
		}
		return toppingList;
	}
}
