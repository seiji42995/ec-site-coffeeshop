package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author seiji_kitahara
 *
 */
@Controller
@RequestMapping("/show")
public class ShowItemListController {

	@Autowired
	private ItemService itemService;

	/**
	 * 全商品情報を取得する.
	 * 
	 * @param model リクエストパラメーター
	 * @return 全商品情報が詰まったリスト
	 */
	@GetMapping("/item-list")
	public String showItemList(Model model) {
		List<Item> itemList = itemService.load();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}

	/**
	 * 検索入力欄より入力された値で検索をする.
	 * 
	 * @param 検索時に入力された文字
	 * @param model       リクエストパラメータ
	 * @return 検索でヒットした商品のリスト。ヒットしなかった場合は全件検索をして返す。
	 */
	@GetMapping("/find-name")
	public String findByName(String name, Model model) {
		List<Item> itemList = itemService.findByName(name);
		if (itemList == null) {
			itemList = itemService.load();
			model.addAttribute("emptyMessage", "該当する商品がありません");
		}

		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}
