package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ShoppingCartForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/show")
public class ShowItemDetailController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/item-detail")
	public String showItemDetail(Model model, Integer itemId, ShoppingCartForm shoppingCartForm) {
		
		Item item = itemService.findByItemId(itemId);
		model.addAttribute("item", item);
		return "item_detail";
	}
}
