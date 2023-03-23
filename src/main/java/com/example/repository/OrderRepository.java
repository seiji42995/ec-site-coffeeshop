package com.example.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static ResultSetExtractor<Order> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {

		Order order = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;

		int oldOrderId = 0;
		int oldOrderItemId = 0;

		while (rs.next()) {
			// 現在の注文IDを取得
			int nowOrderId = rs.getInt("o_id");
			// 現在の注文商品IDを取得
			int nowOrderItemId = rs.getInt("oi_id");

			if (oldOrderId != nowOrderId) {
				order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));

				// 空の注文量品が詰まったリストを作成
				orderItemList = new LinkedList<>();
				order.setOrderItemList(orderItemList);
			}

			if (oldOrderItemId != nowOrderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				Character size = rs.getString("oi_size").charAt(0);
				orderItem.setSize(size);

				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));

				orderItem.setItem(item);

				orderToppingList = new LinkedList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}

			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));

				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));

				orderTopping.setTopping(topping);
				orderToppingList.add(orderTopping);
			}

			oldOrderId = nowOrderId;
			oldOrderItemId = nowOrderItemId;
		}

		return order;
	};
	
	private final static ResultSetExtractor<List<Order>> ORDERLIST_RESULT_SET_EXTRACTOR = (rs) -> {

		List<Order> orderList  = new ArrayList<>();
		Order order = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;

		int oldOrderId = 0;
		int oldOrderItemId = 0;

		while (rs.next()) {
			// 現在の注文IDを取得
			int nowOrderId = rs.getInt("o_id");
			// 現在の注文商品IDを取得
			int nowOrderItemId = rs.getInt("oi_id");

			if (oldOrderId != nowOrderId) {
				order = new Order();
				order.setId(rs.getInt("o_id"));
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));

				// 空の注文量品が詰まったリストを作成
				orderItemList = new LinkedList<>();
				order.setOrderItemList(orderItemList);
			}

			if (oldOrderItemId != nowOrderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				Character size = rs.getString("oi_size").charAt(0);
				orderItem.setSize(size);

				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));

				orderItem.setItem(item);

				orderToppingList = new LinkedList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
			}

			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));

				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));

				orderTopping.setTopping(topping);
				orderToppingList.add(orderTopping);
			}
			if(oldOrderId != nowOrderId) {
				orderList.add(order);				
			}

			oldOrderId = nowOrderId;
			oldOrderItemId = nowOrderItemId;
		}

		return orderList;
	};

	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o.id AS o_id, o.user_id AS o_user_id, o.status AS o_status,");
		sql.append(
				"o.total_price AS o_total_price, o.order_date AS o_order_date, o.destination_name AS o_destination_name,");
		sql.append(" o.destination_email AS o_destination_email, o.destination_zipcode AS destination_zipcode,");
		sql.append(" o.destination_address AS destination_address, o.destination_tel AS destination_tel,");
		sql.append(" o.delivery_time AS delivery_time, o.payment_method AS payment_method,");
		sql.append(
				" oi.id AS oi_id, oi.item_id AS oi_item_id, oi.order_id AS oi_order_id, oi.quantity AS oi_quantity, oi.size AS oi_size,");
		sql.append(" ot.id AS ot_id, ot.topping_id AS ot_topping_id, ot.order_item_id AS ot_order_item_id,");
		sql.append(" t.id AS t_id, t.name AS t_name, t.price_m AS t_price_m, t.price_l AS t_price_l,");
		sql.append(" i.id AS i_id, i.name AS i_name, i.description AS i_description, i.price_m AS i_price_m,");
		sql.append(" i.price_l AS i_price_l, i.image_path AS i_image_path, i.deleted AS i_deleted ");
		sql.append("FROM orders AS o ");
		sql.append("LEFT JOIN order_items AS oi ON o.id = oi.order_id ");
		sql.append("LEFT JOIN order_toppings AS ot ON oi.id = ot.order_item_id ");
		sql.append("LEFT JOIN toppings AS t ON ot.topping_id = t.id ");
		sql.append("LEFT JOIN items AS i ON i.id = oi.item_id WHERE o.user_id = :userId AND o.status = :status ");
		sql.append("ORDER BY oi_id;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		Order order = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR);
		return order;
	}
	
	public List<Order> purchaseHistory(Integer userId, Integer status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o.id AS o_id, o.user_id AS o_user_id, o.status AS o_status,");
		sql.append(
				"o.total_price AS o_total_price, o.order_date AS o_order_date, o.destination_name AS o_destination_name,");
		sql.append(" o.destination_email AS o_destination_email, o.destination_zipcode AS destination_zipcode,");
		sql.append(" o.destination_address AS destination_address, o.destination_tel AS destination_tel,");
		sql.append(" o.delivery_time AS delivery_time, o.payment_method AS payment_method,");
		sql.append(
				" oi.id AS oi_id, oi.item_id AS oi_item_id, oi.order_id AS oi_order_id, oi.quantity AS oi_quantity, oi.size AS oi_size,");
		sql.append(" ot.id AS ot_id, ot.topping_id AS ot_topping_id, ot.order_item_id AS ot_order_item_id,");
		sql.append(" t.id AS t_id, t.name AS t_name, t.price_m AS t_price_m, t.price_l AS t_price_l,");
		sql.append(" i.id AS i_id, i.name AS i_name, i.description AS i_description, i.price_m AS i_price_m,");
		sql.append(" i.price_l AS i_price_l, i.image_path AS i_image_path, i.deleted AS i_deleted ");
		sql.append("FROM orders AS o ");
		sql.append("LEFT JOIN order_items AS oi ON o.id = oi.order_id ");
		sql.append("LEFT JOIN order_toppings AS ot ON oi.id = ot.order_item_id ");
		sql.append("LEFT JOIN toppings AS t ON ot.topping_id = t.id ");
		sql.append("LEFT JOIN items AS i ON i.id = oi.item_id WHERE o.user_id = :userId AND o.status >= :status ");
		sql.append("ORDER BY oi_id;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql.toString(), param, ORDERLIST_RESULT_SET_EXTRACTOR);
		if(orderList.size() == 0) {
			return null;
		}
		
		return orderList;
	}

	public void firstInsert(Integer userId, Integer status) {
		String insertSql = "INSERT INTO orders(user_id, status, total_price) VALUES(:userId, :status, :totalPrice);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status)
				.addValue("totalPrice", 0);
		template.update(insertSql, param);
	}

	public Order findByOrderId(Integer orderId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT o.id AS o_id, o.user_id AS o_user_id, o.status AS o_status,");
		sql.append(
				"o.total_price AS o_total_price, o.order_date AS o_order_date, o.destination_name AS o_destination_name,");
		sql.append(" o.destination_email AS o_destination_email, o.destination_zipcode AS destination_zipcode,");
		sql.append(" o.destination_address AS destination_address, o.destination_tel AS destination_tel,");
		sql.append(" o.delivery_time AS delivery_time, o.payment_method AS payment_method,");
		sql.append(
				" oi.id AS oi_id, oi.item_id AS oi_item_id, oi.order_id AS oi_order_id, oi.quantity AS oi_quantity, oi.size AS oi_size,");
		sql.append(" ot.id AS ot_id, ot.topping_id AS ot_topping_id, ot.order_item_id AS ot_order_item_id,");
		sql.append(" t.id AS t_id, t.name AS t_name, t.price_m AS t_price_m, t.price_l AS t_price_l,");
		sql.append(" i.id AS i_id, i.name AS i_name, i.description AS i_description, i.price_m AS i_price_m,");
		sql.append(" i.price_l AS i_price_l, i.image_path AS i_image_path, i.deleted AS i_deleted ");
		sql.append("FROM orders AS o ");
		sql.append("LEFT JOIN order_items AS oi ON o.id = oi.order_id ");
		sql.append("LEFT JOIN order_toppings AS ot ON oi.id = ot.order_item_id ");
		sql.append("LEFT JOIN toppings AS t ON ot.topping_id = t.id ");
		sql.append("LEFT JOIN items AS i ON i.id = oi.item_id WHERE o.id = :orderId ORDER BY oi_id;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		Order order = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR);
		return order;
	}

	public int update(Order order) {
		StringBuilder updateSql = new StringBuilder();
		System.out.println("update内");
		System.out.println(order);
		updateSql.append(
				"UPDATE orders SET status = :status, total_price = :totalPrice, order_date = :orderDate, destination_name = :destinationName, ");
		updateSql.append(
				"destination_email = :destinationEmail, destination_zipcode = :destinationZipcode, destination_address = :destinationAddress, ");
		updateSql.append(
				"destination_tel = :destinationTel, delivery_time = :deliveryTime, payment_method = :paymentMethod WHERE id = :id");
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		int num = template.update(updateSql.toString(), param);
		return num;
	}

}
