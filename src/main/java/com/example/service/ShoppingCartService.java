package com.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.ShippingForm;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;

	public void insert(ShoppingCartForm shoppingCartForm, Integer userId) {

		// 新規で注文を作成し、注文商品と注文トッピング情報をDBに挿入する
		if (orderRepository.findByUserIdAndStatus(userId, 0) == null) {
			orderRepository.firstInsert(userId, 0);
			Integer orderId = orderRepository.findByUserIdAndStatus(userId, 0).getId();
			orderItemRepository.insert(shoppingCartForm, orderId);

			if (shoppingCartForm.getToppingList() != null) {
				System.out.println("ここに入った");
				Integer orderItemId = orderItemRepository.load(orderId).getId();
				orderToppingRepository.insert(shoppingCartForm, orderItemId);
			}
			// 既存の注文に商品を追加する
		} else {
			Integer orderId = orderRepository.findByUserIdAndStatus(userId, 0).getId();
			orderItemRepository.insert(shoppingCartForm, orderId);

			if (shoppingCartForm.getToppingList() != null) {
				Integer orderItemId = orderItemRepository.load(orderId).getId();
				orderToppingRepository.insert(shoppingCartForm, orderItemId);
			}
		}
	}

	public Order load(Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		return order;
	}

	public Order findByOrderId(Integer orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		return order;
	}
	
	public int update(ShippingForm shippingForm, Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		if(shippingForm.getPaymentMethod() == 1) {
			order.setPaymentMethod(1);
			order.setStatus(1);
		}else if(shippingForm.getPaymentMethod() == 2) {
			order.setPaymentMethod(2);
			order.setStatus(2);
		}
		LocalDate date = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		order.setOrderDate(sqlDate);
		order.setDestinationName(shippingForm.getName());
		order.setDestinationEmail(shippingForm.getEmail());
		order.setDestinationZipcode(shippingForm.getZipcode());
		order.setDestinationAddress(shippingForm.getAddress());
		order.setDestinationTel(shippingForm.getTelephone());
		order.setTotalPrice(shippingForm.getTotalPrice());
		
		// shippingForm内のDeliveryDate（String）をDate型に変換
		try {
			Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd-hh").parse(shippingForm.getDeliveryDate() + "-" + shippingForm.getDeliveryTime());
			order.setDeliveryTime(new Timestamp(deliveryTime.getTime()));
		}catch(Exception e) {
			System.err.println("例外が発生しました");
			e.printStackTrace();
		}
		
		int num = orderRepository.update(order);
		return num;
	}
	
	/**
	 * 削除ボタンを押下した時に対象の注文商品を削除する.
	 * 
	 * @param orderItemId 削除対象とする注文商品
	 */
	public void delete(Integer orderItemId) {
		orderItemRepository.delete(orderItemId);
	}

}
