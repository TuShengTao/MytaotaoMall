// 我的购物车页面 js代码 myCar.html
$(document).ready(function(){
    console.log("此页面加载完，输出购物车cookie  "+$.cookie("TT_CART"));
});
// 点击 我的购物车 触发
function goToMyCar() {
    window.location.href="http://localhost:8082/superMarket/myCart.html";// 跳转到我的购物车页面
}
// myCar.html加载完成 执行，显示加入购物车的商品，不去后台请求...
$(document).ready(function () {
    var resultList=[];
    getCartItemInfo(resultList);//获取详细信息
    console.log("输出结果list"+resultList[0].id);
    var $goodsList=$.cookie("TT_CART");
    $goodsList = $.parseJSON($goodsList);   //转化为 json对象
    // 对 cookie 里的商品进行遍历，然后jquery对dom操作 动态生成html
    for(var i=0;i<$goodsList.length;i++){
       //   id  title num price image
        var $id=$goodsList[i].id;
        var $title=resultList[i].title;//目前为空
        var $num=$goodsList[i].num;
        var $price=$goodsList[i].price/100;
        var $image=resultList[i].image;// 目前为空
        var $inputIdNum='num'+$id; // 该商品数量 input的 id
        var $divId='div'+$id;// 该商品div的 id

        var $goodHtml='<div id="' + $divId + '" data-bind="rowid:1" class="item item_selected " xmlns="http://www.w3.org/1999/html">\n' +
            '\t\t        <div class="item_form clearfix">\n' +
            '\t\t            <div class="cell p-checkbox"><input data-bind="cbid:1" class="checkbox" type="checkbox" name="checkItem" checked="" value="11345721-1"></div>\n' +
            '\t\t            <div class="cell p-goods">\n' +
            '\t\t                <div class="p-img">\n' +
            '\t\t                \t<a href="/superMarket/item/'+$id+'.html" target="_blank">\n' +
            '\t\t                \t\t<img clstag="clickcart|keycount|xincart|p-imglistcart" src="'+$image+'" alt="'+$title+'" width="52" height="52">\n' +
            '\t\t                \t</a>\n' +
            '\t\t                </div>    \n' +
            '\t\t                <div class="p-name">\n' +
            '\t\t                \t<a href="/superMarket/item/'+$id+'.html" clstag="clickcart|keycount|xincart|productnamelink" target="_blank">'+$title+'</a>\n' +
            '\t\t                \t<span class="promise411 promise411_11345721" id="promise411_11345721"></span>\n' +
            '\t\t                </div>    \n' +
            '\t\t            </div>\n' +
            '\t\t            <div class="cell p-price"><span class="price"><em style="color: red;">¥  '+$price+'</em> </span></div>\n' +
            '\t\t            <div class="cell p-promotion"></div>\n' +
            '\t\t            <div class="cell p-inventory stock-11345721">有货</div>\n' +
            '\t\t            <div class="cell p-quantity" for-stock="for-stock-11345721">\n' +
            '\t\t                <div class="quantity-form" data-bind="">\n' +
            '\t\t                    <a href="javascript:void(0);" class="decrement" clstag="clickcart|keycount|xincart|diminish1" onclick="reduceNum('+$id+','+$inputIdNum+')" id="decrement">-</a>\n' +
            '\t\t                    <input type="text" class="quantity-text" itemPrice="'+$price+'" itemId="'+$id+'" value="'+$num+'" id="'+$inputIdNum+'">\n' +
            '\t\t                    <a href="javascript:void(0);" class="increment" clstag="clickcart|keycount|xincart|add1" onclick="addNum('+$id+','+$inputIdNum+')" id="increment" >+</a>\n' +
            '\t\t                </div>\n' +
            '\t\t            </div>\n' +
            '\t\t            <div class="cell p-remove"><a id="remove-11345721-1" data-more="removed-87.20-1" clstag="clickcart|keycount|xincart|btndel318558" class="cart-remove" href="javascript:void(0);" onclick="deleteGood('+$divId+','+$id+')">删除</a>\n' +
            '\t\t            </div>\n' +
            '\t\t        </div>\n' +
            '\t        </div> ';
        $('#product-list').append($goodHtml);// 商品加入购物车
    };
    //循环结束 计算总价格
    allCarPrice();
});
//根据购物车cookie商品的id获取 商品的title image 等信息
function getCartItemInfo(resultList) {
    var goodsId=[];
    var $goodsInfo=$.cookie('TT_CART');// 从cookie 取
    var goodsJson = $.parseJSON($goodsInfo);// 转换为json对象
    // 循环遍历
    for (var i = 0; i < goodsJson.length; i++) {
        goodsId.push(parseInt(goodsJson[i].id));
    }
    $.ajax({
        url:"http://localhost:8082/cart/ getCartItemInfo.html",
        type: "POST",
        data:{
            goodsId:goodsId
        },
        async:false, // 使用ajax的同步，默认是异步
        dataType: "json",
        traditional: true,//这里设置为true 否则数组传入不进后台
        success: function (result) {
            console.log(result);
            console.log(toString.call(result));
            for (var i = 0; i <result.length ; i++) {
                resultList.push(result[i]);
            }
        },
        error: function () {            //失败，回调函数
            alert("查询购物车商品详细信息失败！服务器错误！");
        }
    });
}
// 购物车详情页 从cookie删除商品 传入商品id 和商品的盒子html div的id
function deleteGood($divId,$goodId) {
    $divId='#div'+$goodId;
    var $goodsInfo=$.cookie('TT_CART');// 从cookie 取
    var goodsJson = $.parseJSON($goodsInfo);// 转换为json对象
    // 循环遍历
    for (var i = 0; i < goodsJson.length; i++) {
        if ($goodId == goodsJson[i].id) {
            //从 cookie删除商品
            goodsJson.splice(i,1);// 删除list的第i个 并且后续元素往前移动 1
            console.log("输出删除后的购物车： ");
            console.log(JSON.stringify(goodsJson));
            // 删除该商品html 删除元素
            $($divId).remove();

           $.removeCookie('TT_CART',{path:"/"});// 删除cookie,{path:"/"},
            //alert("删除cookie成功！");
            $.cookie('TT_CART',null,{path:"/"});
            $.cookie('TT_CART', JSON.stringify(goodsJson),{path:"/"});// 再次写入cookie
            console.log("删除该商品后的cookie是： " + $.cookie('TT_CART') );
            break; //删除完 退出循环
        }
    }
    // 更新总价
    allCarPrice();
};
//购物车详情页 减少数量 '+$inputIdNum+'
function reduceNum($goodId,$inputId) {
    $inputId='#num'+$goodId;
    var $nowNum=$($inputId).val();
    if ($nowNum ==1){
        var $GoodNum=1;
    }else {
        var $GoodNum=parseInt($($inputId).val())-1;
    }
    $($inputId).val($GoodNum);
    modifyNum($GoodNum,$goodId);// cookie 修改
    // 更新总价
    allCarPrice();
};
//购物车详情页 添加数量
function addNum($goodId,$inputId) {
    $inputId='#num'+$goodId;
    var $GoodsNum=parseInt($($inputId).val())+1;
    $($inputId).val($GoodsNum);
    modifyNum($GoodsNum,$goodId);// cookie 修改
    // 更新总价
    allCarPrice();

};
//  购物车详情页 修改cookie 里的商品数量 传入一个数量 和修改此商品的id
function modifyNum($goodNum,$goodId){
    var $goodsInfo=$.cookie('TT_CART');// 从cookie 取
    if ($goodId!=null) {
        var goodsJson = $.parseJSON($goodsInfo);
        for (var i = 0; i < goodsJson.length; i++) {
            if ($goodId == goodsJson[i].id) {
                //修改商品数量
                goodsJson[i].num = $goodNum;
                $.cookie('TT_CART',null,{path:"/"});
                $.cookie('TT_CART', JSON.stringify(goodsJson),{path:"/"});// 再次写入cookie 必须加 {path:"/"} 这样才能保证只有一个TT_CART
                var $goodsAfter = $.cookie('TT_CART'); // 控制台检测
                var goodsAfterJson = $.parseJSON($goodsAfter);
                console.log("修改后的商品数量是： " + goodsAfterJson[i].num + "   商品id是  " + goodsAfterJson[i].id);
                break; //修改完 退出循环
            }
        }
    }
};
// 计算购物车总价格，实现思路：在每次修改数量或者删除购物车商品时 动态修改总价格
// 总价格 主要是获取cookie 里的商品信息
function allCarPrice() {
    var $totalPrice=0;//购物车总价格
    var $goodsList=$.cookie("TT_CART");
    $goodsList = $.parseJSON($goodsList);   //转化为 json对象
    var $tatalNum=$goodsList.length;// 购物车商品种类数量
    // 对 cookie 里的商品进行遍历
    for(var i=0;i<$goodsList.length;i++){
        //   id  title num price image
        var $num=$goodsList[i].num;
        var $price=$goodsList[i].price/100;
        var $singlePrice=$num*$price;// 计算单个商品的所有价格
        $totalPrice=$totalPrice+$singlePrice;
    };
    var $carHtml='<div id="allprice" class="total fr">\n' +
        '                  总计（不含运费）：\n' +
        '                  <span class="totalSkuPrice">¥ '+$totalPrice+'</span>\n' +
        '              </div>';
    $('#allprice').remove();
    $('#toOrder').append($carHtml);

};



