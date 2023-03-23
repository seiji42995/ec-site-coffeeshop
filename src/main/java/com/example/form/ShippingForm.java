package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * お届け先情報を受け取るためのフォームクラス.
 * 
 * @author seiji_kitahara
 *
 */
public class ShippingForm {

	/** 注文ID */
	private Integer orderId;
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$", message = "メールアドレスの形式が不正です")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	/** 郵便番号 */
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	@NotBlank(message = "郵便番号を入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
	@Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	@NotBlank(message = "電話番号を入力してください")
	private String telephone;
	/** 配達日 */
	@NotBlank(message = "配達日を選択してください")
	private String deliveryDate;
	/** 配達時刻 */
	@NotBlank(message = "配達希望時刻を選択してください")
	private String deliveryTime;
	/**
	 * 支払い方法 1.代金引換 2.クレジットカード
	 */
	private Integer paymentMethod;
	/** 合計金額 */
	private Integer totalPrice;

	@Override
	public String toString() {
		return "ShippingForm [orderId=" + orderId + ", name=" + name + ", email=" + email + ", zipcode=" + zipcode
				+ ", address=" + address + ", telephone=" + telephone + ", deliveryDate=" + deliveryDate
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + ", totalPrice=" + totalPrice
				+ "]";
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
