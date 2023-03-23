package com.example.form;

import java.util.List;

/**
 * 商品詳細画で選択した値を入れるためのフォームクラス.
 * 
 * @author seiji_kitahara
 *
 */
public class ShoppingCartForm {
	
	/** 商品ID */
	private Integer itemId;
	/** 量 */
	private Integer quantity;
	/**  商品サイズ */
	private Character size;
	/**  カートに追加するトッピングのIDリスト */
	private List<Integer> toppingList;
	
	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingList="
				+ toppingList + "]";
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public List<Integer> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}
}
