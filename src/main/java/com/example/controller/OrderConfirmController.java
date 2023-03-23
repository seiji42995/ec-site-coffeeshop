package com.example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.ShippingForm;
import com.example.repository.UserRepository;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping-cart")
public class OrderConfirmController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession session;

	@PostMapping("/confirm")
	public String confirm(Model model, Integer orderId, ShippingForm shippingForm) {

		Order order = shoppingCartService.findByOrderId(orderId);
		model.addAttribute("order", order);
		User user = new User();
		model.addAttribute("user", user);
		return "order_confirm";
	}

	@PostMapping("/get-address")
	public String getAddress(Model model, Integer orderId) {
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		User user = userRepository.findByUserId(userId);
		ShippingForm shippingForm = new ShippingForm();
		BeanUtils.copyProperties(user, shippingForm);
		model.addAttribute("shippingForm", shippingForm);
		Order order = shoppingCartService.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "order_confirm";
	}

	@PostMapping("/to-finished")
	public String toFinished(@Validated ShippingForm shippingForm, BindingResult result, Model model, Integer orderId) {

		if (shippingForm.getDeliveryDate().equals("")) {
			return confirm(model, orderId, shippingForm);
		}
		
		LocalDate nowDate = LocalDate.now();
		System.out.println("nowDate : " + nowDate);
		System.out.println("form : " + shippingForm.getDeliveryDate());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate orderDate = LocalDate.parse(shippingForm.getDeliveryDate(), formatter);
//		LocalDate orderDate = nowDate.atStartOfDay();
		System.out.println("orderDate : " + orderDate);
//		LocalDateTime dateTime = date.atStartOfDay();
//		System.out.println(dateTime);

		// 現在時刻より前の時刻か確認
		if (orderDate.isBefore(nowDate)) {
			System.out.println("こっちに入っている");
			FieldError fieldError = new FieldError(result.getObjectName(), "deliveryDate",
					"配達日が正しくありません。もう一度配達日を確認してください。");
			result.addError(fieldError);
		}
		
		LocalDateTime orderDateTime = orderDate.atStartOfDay();
		LocalDateTime todayLastOrderTime = orderDateTime;
		todayLastOrderTime = todayLastOrderTime.plusHours(18);
		orderDateTime = orderDateTime.plusHours(Integer.parseInt(shippingForm.getDeliveryTime()));
		LocalDateTime nowDateTime = LocalDateTime.now();
		
		// 現在時刻と同日での確認
		if (orderDate.isEqual(nowDate)) {
			if(nowDateTime.isAfter(todayLastOrderTime) || nowDateTime.plusHours(3).isAfter(todayLastOrderTime)) {
				FieldError fieldError = new FieldError(result.getObjectName(), "deliveryTime",
						"本日の配達可能時間は終了しました。明日の日付に変更して再度入力してください。");
				result.addError(fieldError);
			}else if (orderDateTime.isBefore(nowDateTime.plusHours(3))) {
				System.out.println("同日の3時間以内を判断");
				System.out.println("ここを確認");
				FieldError fieldError = new FieldError(result.getObjectName(), "deliveryTime",
						"現在より3時間後より指定可能です。もう一度配達時間を確認してください。");
				result.addError(fieldError);
			}
		}

		if (result.hasErrors()) {
			System.out.println("エラー発生");
			Order order = shoppingCartService.findByOrderId(orderId);
			model.addAttribute("order", order);
			return "order_confirm";
		}
		System.out.println("注文完了までもう少し");
		System.out.println(shippingForm);

		
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		int updateNum = shoppingCartService.update(shippingForm, userId);
		System.out.println(updateNum + "件更新");

		return "redirect:/shopping-cart/finished";
	}

	@GetMapping("/finished")
	public String orderFinished() {
		return "order_finished";
	}
}
