package com.example.domain;

import java.util.List;

public class OrderItem {

	/** 注文商品ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品情報 */
	private Item item;
	/** 注文トッピング情報が詰まったリスト */
	private List<OrderTopping> orderToppingList;

	public OrderItem() {
	}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<OrderTopping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}

	/**
	 * 各注文商品の小計を計算する. 
	 * （サイズに応じた商品金額とトッピング金額を足し合わせる）
	 * 
	 * @return
	 */
	public int getSubTotal() {
		int subTotal = 0;
		if(this.size == 'M') {
			subTotal = subTotal + this.item.getPriceM() * this.quantity;
		}else {
			subTotal = subTotal + this.item.getPriceL() * this.quantity;
		}
		
		for (OrderTopping orderTopping : this.orderToppingList) {
			if (this.size == 'M') {
				subTotal += orderTopping.getTopping().getPriceM();
			} else {
				subTotal += orderTopping.getTopping().getPriceL();
			}
		}
		return subTotal;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
