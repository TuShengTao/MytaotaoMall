// 支付思路
// 点击购物车详情页面myCart.html的 (点击去结算按钮) 然后跳转到 order-cart.jsp订单确认页面 ，在此页面可以修改
    //收货地址 数量 (点击确认订单，后台生成订单orderId) 然后跳转到 支付商品展示页面 展示订单号orderId/购买的商品信息,订单总价
    // 此页面可以选择支付方式：
    // 支付宝支付 点击后跳转到 支付宝支付页面 展示支付二维码 支付成功还有回调url跳转到 本地页面 success.jsp
$(document).ready(function () {
    var $idList =localStorage.getItem("ItemToOrderId");
    console.log($.parseJSON($idList));
    console.log($.parseJSON($idList)[0]);
});