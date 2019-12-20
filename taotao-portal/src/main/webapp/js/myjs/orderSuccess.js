$(document).ready(function () {
    showAddOrderItem();
    var $orderId=localStorage.getItem("TAOTAO_ORDER_ID"); //获取订单号
    $("#successOrderId").html('订单号 ： '+$orderId);
});

function goToPay() {
    var $orderId=localStorage.getItem("TAOTAO_ORDER_ID"); //获取订单号
    var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE");// 获取总价
    alert($orderId);
    alert($totalPrice);
   var $href = "http://localhost:8082/alipay/goAlipay.html?orderId="+$orderId+"&totalPrice="+$totalPrice;
   alert($href);
    if ($orderId !="" && $totalPrice != ""){
       window.location.href=$href;
    }else {
        alert("请返回页面重新下单！");
    }
}

function showAddOrderItem() {
    //  循环遍历加入订单的商品id
    var $addToOrderIdList=$.parseJSON(localStorage.getItem("ItemToOrderId"));// 获取订单里的商品id
    var $itemImageTitleList=$.parseJSON(localStorage.getItem("ItemImageTitle"));// 商品 图片 title 不需要加入订单
    var $cookOrderItems=$.cookie('TT_CART');
    $cookOrderItems = $.parseJSON($cookOrderItems);// 购物车 获取价格 和购买的数量
    var $orderItemsList=[]; // 保存购买商品的数组
    // 循环加入 orderItemsList[]  包含 image title
    for (var i = 0; i <$addToOrderIdList.length ; i++) {
        for (var j = 0; j <$cookOrderItems.length ; j++) {
            if ($addToOrderIdList[i]==$cookOrderItems[j].id){
                var $buyItemInfo=  {
                    "itemId":$cookOrderItems[j].id,
                    "num": $cookOrderItems[j].num,
                    "price":$cookOrderItems[j].price/100,
                    "totalFee":$cookOrderItems[j].num * $cookOrderItems[j].price/100,
                    "image":null,
                    "title":null
                };
                $orderItemsList.push($buyItemInfo);
            }
        }
    }
    // 遍历 $orderItemsList 和 $itemImageTitleList 填充$orderItemsList的 image、 title
    for (var i = 0; i <$orderItemsList.length ; i++) {
        for (var j = 0; j <$itemImageTitleList.length ; j++) {
            if ($orderItemsList[i].itemId==$itemImageTitleList[j].id) {
                $orderItemsList[i].image=$itemImageTitleList[j].image;
                $orderItemsList[i].title=$itemImageTitleList[j].title;
            }
        }
    }
    // 在订单页面循环显示商品
    for (var i = 0; i <$orderItemsList.length ; i++) {
        var $goodsHtml='\t\t<div id="div157477570654586" data-bind="rowid:1" class="item item_selected " xmlns="http://www.w3.org/1999/html">\n' +
            '\t\t\t\t\t\t<div class="item_form clearfix">\n' +
            '\t\t\t\t\t\t\t<div class="cell p-checkbox"></div>\n' +
            '\t\t\t\t\t\t\t<div class="cell p-goods">\n' +
            '\t\t\t\t\t\t\t\t<div class="p-img">\n' +
            '\t\t\t\t\t\t\t\t\t<a href="/superMarket/item/'+$orderItemsList[i].itemId+'.html" target="_blank">\n' +
            '\t\t\t\t\t\t\t\t\t\t<img clstag="clickcart|keycount|xincart|p-imglistcart" src="'+$orderItemsList[i].image+'" alt="" width="52" height="52">\n' +
            '\t\t\t\t\t\t\t\t\t</a>\n' +
            '\t\t\t\t\t\t\t\t</div>\n' +
            '\t\t\t\t\t\t\t\t<div class="p-name">\n' +
            '\t\t\t\t\t\t\t\t\t<a href="/superMarket/item/'+$orderItemsList[i].itemId+'.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">'+$orderItemsList[i].title+'</a>\n' +
            '\t\t\t\t\t\t\t\t\t<span class="promise411 promise411_11345721" id="promise411_11345721"></span>\n' +
            '\t\t\t\t\t\t\t\t</div>\n' +
            '\t\t\t\t\t\t\t</div>\n' +
            '\t\t\t\t\t\t\t<div class="cell p-price"><span class="price"><em style="color: red; --darkreader-inline-color:#e40000;" data-darkreader-inline-color="">¥ '+$orderItemsList[i].price+'</em> </span></div>\n' +
            '\t\t\t\t\t\t\t<div class="cell p-promotion"></div>\n' +
            '\t\t\t\t\t\t\t<div class="cell p-inventory stock-11345721">有货</div>\n' +
            '\t\t\t\t\t\t</div>\n' +
            '\t\t\t\t</div> ';
        $("#product-list").append($goodsHtml);
    }
}