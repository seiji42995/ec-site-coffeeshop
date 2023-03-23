/**
 * 商品詳細画面の計算を行う
 */
"use strict"
$(function() {
	calc_price();
	$(".size").on("change", function(){
		calc_price();
	});
	
	$(".checkbox").on("click", function(){
		calc_price();
	});
	
	$("#itemNum").on("change", function(){
		calc_price();
	});
	
	// 金額計算を行う関数
	function calc_price() {
		let size = $(".size:checked").val();
		let topping_count = $(".checkbox:checked").length;
		let item_num = $("#itemNum").val();
		let item_price = 0;
		let topping_price = 0;
		if (size === "M") {
			item_price = $("#priceM").text();
			topping_price = 200 * topping_count;
		} else {
			item_price = $("#priceL").text();
			topping_price = 300 * topping_count;
		}
		let removeComma = item_price.replace(/,/g, '');
		let num = parseInt(removeComma, 10);
		let price = (num + topping_price) * item_num;
		$("#totalPrice").text(price.toLocaleString());
	}
});
