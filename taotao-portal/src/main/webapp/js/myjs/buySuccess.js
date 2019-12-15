$(document).ready(function () {
    var $orderId=localStorage.getItem("TAOTAO_ORDER_ID"); //获取订单号
    var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE");// 获取总价
    $("#orderId").html('订单号 ： '+$orderId);
    $("#allPrice").html($totalPrice);
});