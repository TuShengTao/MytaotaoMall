// 商品详情页 在item.html有关购物车功能的js代码
$(document).ready(function(){
    console.log("此页面加载完，输出购物车cookie  "+$.cookie("TT_CART"));
});
function addToCar($carUrl) {
    // $select 是 添加购物车按钮的 id
    var $href=$($carUrl).val();
    var $num=parseInt($('#buy-num').val()); // 获取数量
    if(goodsIfExit()){
        alert("此商品已添加进购物车！")
    } else {
        $.ajax({
            url:$href,
            type: "GET",
            data:{
                num:$num
            },
            xhrFields: {    //设置 带上cookie
                withCredentials: true
            },
            dataType: "json",                //数据返回类型，可以是xml、json等
            success: function (result) {      //成功，回调函数
                //成功以后 进行跳转 到成功页面
                console.log("输出购物车cookie"+$.cookie("TT_CART"));
                window.location.href="http://localhost:8082/cart/success.html";//跳转到成功页面
            },
            error: function () {            //失败，回调函数
                alert("加入购物车失败！服务器错误！");
            }
        });
    }

};
function getGoodsId() {
    var $idNum=$id.substring(0,$id.indexOf('.'));
    return $idNum;// 返回商品id
}
//  判断商品是否存在于购物车 和 购物车是否存在
function goodsIfExit() {
    var $goodsInfo=$.cookie('TT_CART');
    var $goodId=getGoodsId();
    var $good_exit=0;// 0表示不存在于购物车
    if ($goodsInfo !=null){
        var goodsJson = $.parseJSON($goodsInfo);
        for (var i = 0; i < goodsJson.length; i++) {
            if ($goodId == goodsJson[i].id) {
                $good_exit=1;
                break;
            }
        }
        if ($good_exit==1){
            return true;    // 存在就 可以不必向服务器再次请求，不执行ajax 返回 true
        } else {
            return false;
        }
    }else {
        return false;
    }
};
//商品详情页 减少数量
function reduceNum($InputId) {
    var $nowNum=$($InputId).val();
    if ($nowNum ==1){
        var $GoodsNum=1;
    }else {
        var $GoodsNum=parseInt($($InputId).val())-1;
    }
    $($InputId).val($GoodsNum);

};
//商品详情页 添加数量
function addNum($InputId) {
    var $GoodsNum=parseInt($($InputId).val())+1;
    $($InputId).val($GoodsNum);
};
// 点击 我的购物车 触发
function goToMyCar() {
    window.location.href="http://localhost:8082/superMarket/myCart.html";// 跳转到我的购物车页面
}
