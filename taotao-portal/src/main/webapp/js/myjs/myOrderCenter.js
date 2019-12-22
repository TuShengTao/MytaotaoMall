$(document).ready(function () {
    var $userId=localStorage.getItem("TAOTAO_USERID");
    $.ajax({
        url:"http://localhost:8082/order/select.html",
        type: "GET",
        data:{
            userId:$userId
        },// 向后台传入一个userid
        dataType: "json",                //数据返回类型，可以是xml、json等
        success: function (result) {      //成功，回调函数
            if (result!="" && result !=null){
                console.log("输出订单信息");
                console.log(result.data);
                console.log(result.data[0]);
                var data=result.data;
                // 遍历所有订单信息
                var orderIdArray=[];
                // 得到所有不同订单号
                for (var i = 0; i <data.length ; i++) {
                    if ($.inArray(data[i].orderId,orderIdArray)==-1){
                        orderIdArray.push(data[i].orderId);// 如果不存在就加入进去
                    }
                };
                console.log("输出订单号");
                console.log(orderIdArray);
                for (var i = 0; i <orderIdArray.length ; i++) {
                    var $orderId=orderIdArray[i]; //订单号
                    var $receiverName=null; // 目前绑定为购买人，并未实现查出真正收货人，实现方法：三张表连接查询。目前是两张表，没连接订单物流表
                    var $payMent=null;// 订单金额
                    var $orderStatus=null;// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
                    var $orderStatusArray=["null","未付款","已付款","未发货","已发货","交易成功","交易关闭"];
                    var $YMD=null;// 年月日
                    var $HMS=null;// 时 分 秒
                    for (var j = 0; j <data.length ; j++) {
                        if ($orderId==data[j].orderId){
                            $receiverName=data[j].buyerNick;
                            $payMent=data[j].payment;
                            $orderStatus=$orderStatusArray[data[j].status];
                            $YMD=timestampToTime(data[j].updateTime,1);
                            $HMS=timestampToTime(data[j].updateTime,2);
                            break;
                        }
                    }
                    var tbOrderId='tb'+$orderId;
                    var SingleOrder='\t<tbody id="'+tbOrderId+'" >\n' +
                        '\t\t\t\t\t\t\t\t<tr class="tr-th">\n' +
                        '\t\t\t\t\t\t\t\t\t<td colspan="6">\n' +
                        '\t\t\t\t\t\t\t\t\t\t<span class="tcol1"> 订单编号: <a name="orderIdLinks"  href="javascript:void(0);" >'+$orderId+'</a></span>\n' +
                        '\t\t\t\t\t\t\t\t\t\t<span class="tcol2">惠淘淘</span>\n' +
                        '\t\t\t\t\t\t\t\t\t\t<span class="tcol3"><a class="btn-im" onclick="getPamsForChat()" href="#none" title="联系客服"></a></span>\n' +
                        '\t\t\t\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t\t\t</tr>\n' +
                        '\t\t\t\t\t\t\t\t<tr id="track2538292730" oty="0,1,70" class="tr-td">\n' +
                        '\t\t\t\t\t\t\t\t\t<td id="'+$orderId+'">\n' +
                        '\n' +
                        '\n' +
                        '\t\t\t\t\t\t\t\t\t</td>\n' +
                        '\n' +
                        '\t\t\t\t\t\t\t\t\t<td>\n' +
                        '\t\t\t\t\t\t\t\t\t\t<div class="u-name" id="username">\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t'+$receiverName+'\n' +
                        '\t\t\t\t\t\t\t\t\t\t</div>\n' +
                        '\t\t\t\t\t\t\t\t\t</td>\n' +
                        '\t\t\t\t\t\t\t\t\t<td> <span id="totalPrice"><em style="color: red">￥ '+$payMent+'</em></span><br> 在线付款<br> </td>\n' +
                        '\t\t\t\t\t\t\t\t\t<td> <span class="ftx-03">'+$YMD+'<br>'+$HMS+'</span> <input type="hidden" id="datasubmit-2538292730" value="2014-10-20 22:30:49"> </td>\n' +
                        '\t\t\t\t\t\t\t\t\t<td><span class="ftx-03"><em style="color: red">'+$orderStatus+'</em></span></td>\n' +
                        '\t\t\t\t\t\t\t\t\t<td id="operate2538292730" class="order-doi" width="100"> <span id="pay-button-2538292730" state=""></span> <a target="_blank" href="http://order.jd.com/normal/item.action?orderid=2538292730&amp;PassKey=769448C6BA99F1ADA8244BAE7BC60580" clstag="click|keycount|orderinfo|order_check">查看</a><span id="order_comment"></span><span class="pop-recycle-a">|<a href="javascript:void(0)" clstag="click|keycount|orderinfo|order_del" onclick="deleteOrderById('+$orderId+')">删除</a></span><span id="doi2538292730"><br><a href="http://club.jd.com/JdVote/TradeComment.aspx?ruleid=2538292730&amp;ot=0&amp;payid=1&amp;shipmentid=70" target="_blank" clstag="click|keycount|orderinfo|order_comment">评价晒单</a><br></span><a href="http://myjd.jd.com/repair/ordersearchlist.action?searchString=2538292730" target="_blank" clstag="click|keycount|orderinfo|order_repair">申请返修/退换货</a> <a class="btn-again" clstag="click|keycount|orderlist|buy" href="http://cart.jd.com/cart/dynamic/reBuyForOrderCenter.action?wids=1113410,1222567&amp;nums=1,1&amp;rid=1419846299535" target="_blank">还要买</a> </td>\n' +
                        '\t\t\t\t\t\t\t\t</tr>\n' +
                        '\t\t\t\t\t\t\t\t</tbody>';

                    $('#allOrderId').append(SingleOrder);
                };
                // 循环吧每个订单的商品 加入订单div  每个订单 是一对多 或 一对一
                for (var i = 0; i <data.length ; i++) {
                    var $tdid='#'+data[i].orderId;
                    var $singleItemHtml='<div class="img-list">\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<a href="javascript:void(0);" class="img-box" clstag="click|keycount|orderinfo|order_product" target="_blank"> <img title="'+data[i].title+'" width="50" height="50" src='+data[i].picPath+' class="err-product"> </a>\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<span class="goodsnumber" id="goodNumber"> x'+data[i].num+'</span>\n' +
                        '\t\t\t\t\t\t\t\t\t\t</div>\n';
                    $($tdid).append($singleItemHtml);
                }

            } else {
                alert("返回订单信息为空！");
            }

        },
        error: function () {            //失败，回调函数
            alert("查订单失败！服务器错误！");
        }
    });
});
// 格式化时间 原时间格式： 1576246917000
function timestampToTime(timestamp,flag) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    if (flag==1){
        return Y+M+D;// 返回 年 月 日
    }
    else {
        return h+m+s;// 返回 时 分 秒
    }
}
function deleteOrderById(orderId){
    var order={
        "orderId":orderId
    };

    $.ajax({
        url:"http://localhost:8082/order/delete.html",
        type: "POST",
        contentType : 'application/json',
        data:JSON.stringify(order),// 向后台传入一个对象
        dataType: "json",                //数据返回类型，可以是xml、json等
        success: function (result) {      //成功，回调函数
            var tbOrderId='#tb'+orderId;
            $(tbOrderId).remove();// 删除成功之后删除html节点
        },
        error: function () {            //失败，回调函数
            alert("删除订单失败！服务器错误！");
        }
    });
}
