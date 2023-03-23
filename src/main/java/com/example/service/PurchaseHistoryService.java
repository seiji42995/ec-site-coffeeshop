package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文履歴情報を操作するサービスクラス.
 * 
 * @author seiji_kitahara
 *
 */
@Service
@Transactional
public class PurchaseHistoryService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * ログイン中のユーザーの注文履歴情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 該当する注文履歴情報（該当情報がない場合はNullを返す）
	 */
	public List<Order> purchaseHistory(Integer userId) {
		Integer status = 1;
		List<Order> orderList = orderRepository.purchaseHistory(userId, status);
		return orderList;
	}
}
