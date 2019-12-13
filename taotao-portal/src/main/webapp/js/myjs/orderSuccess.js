
function goToPay() {
    var $orderId=localStorage.getItem("TAOTAO_ORDER_ID"); //获取订单号
    var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE");// 获取总价
    //确认提交订单成功后, 进入购买页面 还需要加 总价格
    // http://localhost:8082/alipay/goAlipay.html?id=2&price=1888
    alert($orderId);
    alert($totalPrice);
   var $href = "http://localhost:8082/alipay/goAlipay.html?orderId="+$orderId+"&totalPrice="+$totalPrice;
   alert($href);
    if ($orderId !="" && $totalPrice != ""){
       window.location.href=$href;
       //  $.ajax({
       //      url:"http://localhost:8082/alipay/goAlipay.html",
       //      type: "GET",
       //      data:{
       //          orderId:$orderId,
       //          totalPrice:$totalPrice
       //      },
       //      //async:false, // 使用ajax的同步，默认是异步
       //      dataType: "json",                //数据返回类型，可以是xml、json等
       //      success: function (result) {      //成功，回调函数
       //          // 跳转到购买成功界面
       //          // window.location.href = "http://localhost:8082/superMarket/buySuccess.html";
       //          alert("操作全部成功");
       //      },
       //      error: function () {            //失败，回调函数
       //          alert("创建订单失败！服务器错误！");
       //      }
       //  });
    }else {
        alert("请返回页面重新下单！");
    }


}
$(document).ready(function () {
    showMyOrder();
});
function showMyOrder() {

}