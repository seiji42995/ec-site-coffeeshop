package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.PurchaseHistoryService;

import jakarta.servlet.http.HttpSession;

/**
 * 注文履歴情報を操作するコントローラークラス.
 * 
 * @author seiji_kitahara
 *
 */
@Controller
@RequestMapping("/purchase-history")
public class PurchaseHistoryController {

	@Autowired
	private PurchaseHistoryService purchaseHistoryService;
	@Autowired
	private HttpSession session;
	
	/**
	 * 注文履歴情報を取得する.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/show-list")
	public String showList(Model model) {
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		List<Order> orderList = purchaseHistoryService.purchaseHistory(userId);
		System.out.println("orderListを確認");
		System.out.println(orderList);
		if(orderList == null) {
			model.addAttribute("noHistory", "注文履歴がありません");
			return "purchase_history";
		}
		model.addAttribute("orderList", orderList);
		return "purchase_history";	
	}
	
}
