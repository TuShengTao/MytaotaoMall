
$(document).ready(function () {
    showAddOrderItem();
});
function showAddOrderItem() {
    //  循环遍历加入订单的商品id
    var $addToOrderIdList=$.parseJSON(localStorage.getItem("ItemToOrderId"));// 获取订单里的商品id
    var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE"); // 订单总价
    var $itemImageTitleList=$.parseJSON(localStorage.getItem("ItemImageTitle"));// 商品 图片 title 不需要加入订单
    var $cookOrderItems=$.cookie('TT_CART');

    console.log("输出测试图片 title");
    console.log($itemImageTitleList);
    console.log("输出测试listorder");
    console.log($addToOrderIdList);
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
        var $goodsHtml=
            '\t<div class="goods-list">\n' +
            '    <h4 class="vendor_name_h" id="0">商家：惠淘淘商城</h4>\t\t         \n' +
            '    <div class="goods-suit goods-last">\n' +
            '\t\t<div class="goods-item goods-item-extra">\n' +
            '\n' +
            '\t\t\t<div class="p-img">\n' +
            '\t\t\t\t<a target="_blank" href="/item/${cart.id}.html">\n' +
            '\t\t\t\t\t<img src="'+$orderItemsList[i].image+'" alt="">\n' +
            '\t\t\t\t</a>\n' +
            '\t\t\t</div>\n' +
            '\t\t\t<div class="goods-msg">\n' +
            '\t\t\t\t<div class="p-name">\n' +
            '\t\t\t\t\t<a href="/item/${cart.id}.html" target="_blank">\n' +
            '\t\t\t\t\t\t'+$orderItemsList[i].title+' \n' +
            '\t\t\t\t\t</a>\n' +
            '\t\t\t\t</div>\n' +
            '\t\t\t\t<div class="p-price">\n' +
            '\t\t\t\t\t<strong>￥'+$orderItemsList[i].price+'</strong>\n' +
            '\t\t\t\t\t<span class="ml20"> x '+$orderItemsList[i].num+' </span>\n' +
            '\t\t\t\t\t<span class="ml20 p-inventory" skuId="11555193">有货</span>\n' +
            '\t\t\t\t</div>\n' +
            '\t\t\t\t<i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span>\n' +
            '\t\t\t</div>\n' +
            '\t\t\t<div class="clr"></div>\n' +
            '\t\t</div>\n' +
            '\t</div>                   \n' +
            '</div>\n' +
            '\n' +
            '<div class="dis-modes">\n' +
            '\t<div class="mode-item mode-tab">\n' +
            '\t\t<h4>\n' +
            '\t\t\t配送方式：（<a id="jd-goods-item" class="ftx-05 alink"\n' +
            '\t\t\t\thref="#none">对应商品</a>）\n' +
            '\t\t</h4>\n' +
            '\t\t<div class="mode-tab-nav">\n' +
            '\t\t\t<ul>\n' +
            '\t\t\t\t<li class="mode-tab-item " id="jd_shipment_item"\n' +
            '\t\t\t\t\tonclick="doSwithTab(\'pay\')"><span\n' +
            '\t\t\t\t\tid="jdShip-span-tip" class="m-txt">惠淘淘快递<i\n' +
            '\t\t\t\t\t\tclass=\'qmark-icon qmark-tip\'\n' +
            '\t\t\t\t\t\tdata-tips=\'由惠淘淘公司负责配送，速度很快，还接受上门刷卡付款服务\'></i></span><b></b></li>\n' +
            '\t\t\t</ul>\n' +
            '\t\t</div>\n' +
            '\t</div>\n' +
            '</div>\n' +
            '<div class="clr"></div>\n' +
            '<div class="freight-cont">\n' +
            '\t<strong class="ftx-01" style="color: #666"\n' +
            '\t\t\tfreightByVenderId="0" popJdShipment="false">免运费</strong>\n' +
            '</div>';
        $("#addToOrderGoodsList").prepend($goodsHtml);
    }
    $("#warePriceId").html("￥"+$totalPrice);
    $("#sumPayPriceId").html("￥"+$totalPrice);
    $("#payPriceId").html("￥"+$totalPrice);


}
// 增加地址
// function addAddress() {
//     var address=[];
//
//     var addressObject=new Object();
//     addressObject.phone=12344543456;
//     addressObject.address="宁波市学院路999号";
//     addressObject.receiver="罗丹";
//     var stringAd1=addressObject;
//
//     address.push(stringAd1);
//
//     addressObject.phone=88888888888;
//     addressObject.address="宁波市学院路888号";
//     addressObject.receiver="屠圣涛";
//     var stringAd2=addressObject;
//
//     address.push(stringAd2);
//     console.log(address);
//
//     var $addToOrderIdList=$.parseJSON(localStorage.getItem("ItemToOrderId"));// 保存订单里的商品id
//     var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE"); // 订单总价
//     var $itemImageTitleList=localStorage.getItem("ItemImageTitle");// 商品 图片 title 不需要的
//     var $userId= localStorage.getItem("TAOTAO_USERID");// 用户id
//     var $cookOrderItems = $.parseJSON($.cookie('TT_CART'));// 购物车 获取价格 和购买的数量
//
//
// }
// 显示地址
// function showAddress() {
//     var $userId= parseInt(localStorage.getItem("TAOTAO_USERID"));
//     $.ajax({
//         url:"http://localhost:8082/user/address.html",
//         type: "GET",
//         data:{
//             id:$userId
//         },
//         async:false, // 使用ajax的同步，默认是异步
//         dataType: "json",
//         //  traditional: true,//这里设置为true 否则数组传入不进后台
//         success: function (result) {
//             console.log("输出用户所有的收货地址：");
//             localStorage.removeItem("USER_ADDRESS");
//             localStorage.setItem("USER_ADDRESS",result.address);// 存入收货地址 如果用户要添加收货时从里面取
//             console.log(result);
//             console.log($.parseJSON(result.address)[0].receiver);
//
//         },
//         error: function () {            //失败，回调函数
//             alert("添加收货地址信息失败！服务器错误！");
//         }
//     });
// }

// 点击确认订单  跳转到订单创建成功 跳转到 orderSuccess.html
function createOrder() {
    var $addToOrderIdList=$.parseJSON(localStorage.getItem("ItemToOrderId"));// 保存订单里的商品id
    var $totalPrice=localStorage.getItem("TAOTAO_ORDER_PRICE"); // 订单总价
    var $itemImageTitleList=$.parseJSON(localStorage.getItem("ItemImageTitle"));// 商品:图片 title
    var $userId= localStorage.getItem("TAOTAO_USERID");// 用户id
    var $cookOrderItems = $.parseJSON($.cookie('TT_CART'));// 购物车 获取价格 和购买的数量

    var $orderItemsList=[]; // 保存购买商品的数组
    // 循环加入 orderItemsList[]
    for (var i = 0; i <$addToOrderIdList.length ; i++) {
        for (var j = 0; j <$cookOrderItems.length ; j++) {
            if ($addToOrderIdList[i]==$cookOrderItems[j].id){
                var $buyItemInfo=  {
                    "itemId":$cookOrderItems[j].id,
                    "num": $cookOrderItems[j].num,
                    "price":$cookOrderItems[j].price/100,
                    "totalFee":$cookOrderItems[j].num * $cookOrderItems[j].price/100,
                    "title":$itemImageTitleList[i].title,
                    "picPath":$itemImageTitleList[i].image
                };
                $orderItemsList.push($buyItemInfo);
            }
        }
    }

    // 传入后台order 数据格式
    var order={
        "payment": $totalPrice,
        "postFee": 0,
        "userId": $userId,
        "buyerMessage": null,
        "buyerNick": "屠圣涛",
        "orderItems":$orderItemsList,
        "orderShipping": {
            "receiverName": "luodan",
            "receiverPhone": "",
            "receiverMobile": "15800000000",
            "receiverState": "上海",
            "receiverCity": "上海",
            "receiverDistrict": "闵行区",
            "receiverAddress": "三鲁公路3279号 明浦广场 3号楼 205室",
            "receiverZip": "200000"
        }
    };


        $.ajax({
            url:"http://localhost:8082/order/create.html",
            type: "POST",
            contentType : 'application/json',
            data:JSON.stringify(order),// 向后台传入一个对象
            dataType: "json",                //数据返回类型，可以是xml、json等
            success: function (result) {      //成功，回调函数
                if (result!="" && result !=null){
                    localStorage.setItem("TAOTAO_ORDER_ID",JSON.stringify(result)); //保存订单号
                    window.location.href = "http://localhost:8082/superMarket/orderSuccess.html";
                } else {
                    alert("返回订单为空！");
                }

            },
            error: function () {            //失败，回调函数
                alert("创建订单失败！服务器错误！");
            }
        });


}


