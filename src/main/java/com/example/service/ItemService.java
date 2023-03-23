package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	
	public List<Item> load(){
		List<Item> itemList = itemRepository.load();
		return itemList;
	}
	
	/**
	 * 商品IDでの主キー検索.
	 * 
	 * @param 検索をかけたい商品ID
	 * @return 検索結果の商品情報（何も見つからない場合はNullを返す）
	 */
	public Item findByItemId(Integer itemId) {
		Item item = itemRepository.findByItemId(itemId);
		List<Topping> toppingList = toppingRepository.load();
		item.setToppingList(toppingList);
		return item;
	}
	
	public List<Item> findByName(String keyword){
		List<Item> itemList = itemRepository.findByName(keyword);
		return itemList;
	}
}
