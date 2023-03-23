package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private HttpSession session;

	@PostMapping("/insert")
	public String insert(ShoppingCartForm shoppingCartForm, Model model) {
		// 確認用として仮に作成。本来はログイン情報からとってくる
		System.out.println(shoppingCartForm);
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		shoppingCartService.insert(shoppingCartForm, userId);
		
		return "redirect:/shopping-cart/show";
	}
	
	@GetMapping("/show")
	public String show(Model model) {
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		Order order = shoppingCartService.load(userId);
		
		if(order.getOrderItemList().size() == 0) {
			model.addAttribute("emptyCart", "カート内に商品がありません" );
		}
		
		model.addAttribute("order", order);
		return "cart_list";
	}
	
	@PostMapping("/delete")
	public String delete(Integer orderItemId, Model model) {
		shoppingCartService.delete(orderItemId);
		return "redirect:/shopping-cart/show";
	}
	
	
}
