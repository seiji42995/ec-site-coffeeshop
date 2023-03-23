package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;
import com.example.form.ShoppingCartForm;

@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {

		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		Character size = rs.getString("size").charAt(0);
		orderItem.setSize(size);
		return orderItem;
	};

	public OrderItem load(Integer orderId) {
		String sql = "SELECT id, item_id, order_id, quantity, size FROM order_items WHERE order_id = :orderId ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		List<OrderItem> orderItemList = template.query(sql, param, ORDER_ITEM_ROW_MAPPER);
		if(orderItemList.size() == 0) {
			return null;
		}
		return orderItemList.get(0);
	}
	
	public void insert(ShoppingCartForm shoppingCartForm, Integer orderId) {
		String insertSql = "INSERT INTO order_items(item_id, order_id, quantity, size) VALUES(:itemId, :orderId, :quantity, :size);";

		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", shoppingCartForm.getItemId())
				.addValue("orderId", orderId).addValue("quantity", shoppingCartForm.getQuantity())
				.addValue("size", shoppingCartForm.getSize());
		template.update(insertSql, param);
	}
	
	public void delete(Integer orderItemId) {
		String sql = "DELETE FROM order_items WHERE id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);
	}
}
